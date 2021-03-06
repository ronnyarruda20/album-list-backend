package com.list.music.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.hibernate.Hibernate;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.list.music.dto.AutorDto;
import com.list.music.model.Autor;
import com.list.music.repository.AutorRepository;

@Service
@Transactional
public class AutorService implements GenericService<Autor> {

	@Autowired
	private AutorRepository repository;

	@Autowired
	private ModelMapper modelMapper;

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

	public void save(AutorDto autorDto) {
		Autor autor = modelMapper.map(autorDto, Autor.class);
		repository.save(autor);
	}

	public void remove(Integer id) {
		Optional<Autor> optional = repository.findById(id);
		this.repository.delete(optional.get());
	}

}
