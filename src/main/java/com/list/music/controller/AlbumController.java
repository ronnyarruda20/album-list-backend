package com.list.music.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;

import com.list.music.dto.AlbumDto;
import com.list.music.model.Album;
import com.list.music.pagination.PageableResponse;
import com.list.music.service.AlbumService;

@RestController
@RequestMapping("/album")
public class AlbumController {

	@Autowired
	private AlbumService service;

//	@Autowired
//	private MinioService minioService;

	@GetMapping("/all")
	public ResponseEntity<?> listaTodosAlbuns() {
		return ResponseEntity.of(service.findAll());
	}

	@GetMapping("/allPaginate")
	public ResponseEntity<?> listaTodosAlbuns(
			@RequestParam(value = "pageNumber", required = false, defaultValue = "0") int page,
			@RequestParam(value = "pageSize", required = false, defaultValue = "5") int size) {
		return ResponseEntity.ok(service.findAll(page, size));
//		return new PageableResponse<AlbumDto>(service.findAll(page, size));
	}

	@GetMapping("/list")
	public PageableResponse<Album> search(@RequestParam(value = "searchTerm", required = false) String searchTerm,
			@RequestParam(value = "pageNumber", required = false, defaultValue = "0") int page,
			@RequestParam(value = "pageSize", required = false, defaultValue = "5") int size) {
		return new PageableResponse<Album>(service.search(searchTerm, page, size));

	}

	@PostMapping("/save")
	public void saveAlbum(@RequestPart AlbumDto albumDto) {
		System.out.println(albumDto);
//		Path path = Path.of(albumDto.getFile().getOriginalFilename());
//		try {
//			minioService.upload(path, albumDto.getFile().getInputStream(), albumDto.getFile().getContentType());
//			InputStream inputStream = minioService.get(Path.of(albumDto.getFile().getOriginalFilename()));
//		} catch (MinioException e) {
//			throw new IllegalStateException("The file cannot be upload on the internal storage. Please retry later", e);
//		} catch (IOException e) {
//			throw new IllegalStateException("The file cannot be read", e);
//		}
	}

}
