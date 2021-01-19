package com.list.music.service;

import java.util.List;
import java.util.Optional;

public interface GenericService<T> {

	Optional<T> findById(Integer id);

	Optional<List<T>> findAll();

}
