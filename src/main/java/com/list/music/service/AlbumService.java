package com.list.music.service;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.apache.http.entity.ContentType;
import org.hibernate.Hibernate;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.util.Base64Utils;
import org.springframework.web.multipart.MultipartFile;

import com.jlefebure.spring.boot.minio.MinioException;
import com.jlefebure.spring.boot.minio.MinioService;
import com.list.music.dto.AlbumDto;
import com.list.music.model.Album;
import com.list.music.repository.AlbumRepository;

@Service
@Transactional
public class AlbumService implements GenericService<Album> {

	@Autowired
	private AlbumRepository repository;

	@Autowired
	private MinioService minioService;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public Optional<Album> findById(Integer id) {
		return this.repository.findById(id);
	}

	@Override
	public Optional<List<Album>> findAll() {
		Optional<List<Album>> albums = Optional.ofNullable(this.repository.findAll());
		albums.get().forEach(p -> Hibernate.initialize(p.getAutor()));
		return albums;
	}

	public Page<Album> findAll(int page, int size) {
		PageRequest pageRequest = PageRequest.of(page, size, Sort.Direction.ASC, "nome");
		Page<Album> albums = repository.findAll(pageRequest);
		albums.getContent().forEach(p -> Hibernate.initialize(p.getAutor()));
		return albums;
	}

	public Page<Album> search(String searchTerm, int page, int size, String sort) {
		PageRequest pageRequest = PageRequest.of(page, size, Direction.valueOf(sort), "nome");
		Page<Album> albums = repository.search(searchTerm == null ? searchTerm : searchTerm.toLowerCase(), pageRequest);
		albums.getContent().forEach(p -> {
			Hibernate.initialize(p.getAutor());
			if (p.getImagem() != null)
				p.setFile(this.loadingFile(p.getImagem()));

		});
		return albums;
	}

	public void saveDto(AlbumDto albumDto) throws MinioException, IOException {

		if (albumDto.getFile() != null) {
			String fileName = this.addImageInAlbum(albumDto.getFile());
			albumDto.setImagem(fileName);
		}
		Album album = modelMapper.map(albumDto, Album.class);
		this.save(album);
	}

	public void save(Album album) {
		repository.save(album);
	}

	public void addImageInAlbum(MultipartFile file, String id) throws MinioException, IOException {
		Optional<Album> optional = repository.findById(Integer.valueOf(id));
		Path path = Path.of(file.getOriginalFilename());
		minioService.upload(path, file.getInputStream(), file.getContentType());
		this.save(optional.get());
	}

	public String addImageInAlbum(String base64) throws MinioException, IOException {
		String fileName = this.getName(base64);
		InputStream is = this.decoder(base64, fileName);
		minioService.upload(Path.of(fileName), is, this.getContentType(is));
		this.clearFile(fileName);
		return fileName;
	}

	public void removeAlbum(Integer id) throws MinioException, IOException {
		Optional<Album> optional = repository.findById(id);
		if (optional.isPresent() && optional.get().getImagem() != null) {
			Path path = Path.of(optional.get().getImagem());
			minioService.remove(path);
		}
		this.repository.delete(optional.get());
	}

	private String loadingFile(String object) {
		try {
			InputStream inputStream = this.minioService.get(Path.of(object));
			return Base64Utils.encodeToString(inputStream.readAllBytes());
		} catch (MinioException e) {
			System.out.println(e);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;

	}

	public InputStream decoder(String base64, String fileName) {
		String partSeparator = ",";
		if (base64.contains(partSeparator)) {

			String encodedImg = base64.split(partSeparator)[1];
			byte[] decodedImg = Base64.getDecoder().decode(encodedImg.getBytes(StandardCharsets.UTF_8));

			try (FileOutputStream fileOuputStream = new FileOutputStream(fileName)) {
				return new ByteArrayInputStream(decodedImg);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	private ContentType getContentType(InputStream is) throws IOException {
		return ContentType.create(URLConnection.guessContentTypeFromStream(is));
	}

	private String getName(String base64) {
		String partSeparator = ",";
		if (base64.contains(partSeparator)) {
			String fileName = base64.split(partSeparator)[1];
			return fileName.substring(1, 100).replace("/", "");
		}
		return null;
	}
	
	private void clearFile(String fileName) {
		File f = new File(fileName);
		f.delete();
	}

}
