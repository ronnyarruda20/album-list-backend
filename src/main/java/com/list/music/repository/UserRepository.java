package com.list.music.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.list.music.model.UserModel;


@Repository
public interface UserRepository extends JpaRepository<UserModel, Integer> {
	
    public Optional<UserModel> findByUserName(@Param("username") String username);
}

