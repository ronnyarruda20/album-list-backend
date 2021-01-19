package com.list.music.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


import com.jlefebure.spring.boot.minio.MinioService;
import com.list.music.model.Album;
import com.list.music.repository.AlbumRepository;

@Service
@Transactional
public class AlbumService implements GenericService<Album> {

	@Autowired
	private AlbumRepository repository;

	@Autowired
	private MinioService minioService;

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
		return  this.repository.findAll(pageRequest);
	}

	public Page<Album> search(String searchTerm, int page, int size) {
		PageRequest pageRequest = PageRequest.of(page, size, Sort.Direction.ASC, "nome");
		return repository.search(searchTerm == null ? searchTerm : searchTerm.toLowerCase(), pageRequest);
	}

	public void save(Album album) {
		repository.save(album);
	}

}
