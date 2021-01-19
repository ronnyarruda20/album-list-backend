package com.list.music.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.list.music.model.Autor;

public interface AutorRepository extends JpaRepository<Autor, Integer> {
}
