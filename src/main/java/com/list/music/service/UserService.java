package com.list.music.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.list.music.model.UserModel;
import com.list.music.repository.UserRepository;


@Service
public class UserService implements GenericService<UserModel> {

	@Autowired
	private UserRepository repository;

	@Override
	public Optional<UserModel> findById(Integer id) {
		return repository.findById(id);
	}

	@Override
	public Optional<List<UserModel>> findAll() {
		return Optional.of(repository.findAll());
	}

	public Optional<UserModel> findByName(String username) {
		return repository.findByUserName(username);
	}

}
