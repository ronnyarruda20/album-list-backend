package com.list.music.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.list.music.model.Album;

@Repository
public interface AlbumRepository extends JpaRepository<Album, Integer> {

	@Query("FROM Album m join m.autor a WHERE (:searchTerm is null or LOWER(m.nome) like %:searchTerm% or LOWER(a.nome) like %:searchTerm%)")
	Page<Album> search(@Param("searchTerm") String searchTerm, Pageable pageable);
	
	
}
