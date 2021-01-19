package com.list.music.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.list.music.model.Autor;
import com.list.music.repository.AutorRepository;

@Service
@Transactional
public class AutorService implements GenericService<Autor> {

	@Autowired
	private AutorRepository repository;

	@Override
	public Optional<Autor> findById(Integer id) {
		return this.repository.findById(id);
	}

	@Override
	public Optional<List<Autor>> findAll() {
		Optional<List<Autor>> autors = Optional.ofNullable(this.repository.findAll());
		autors.get().forEach(p -> Hibernate.initialize(p.getAlbums()));
		return autors;
	}

}
