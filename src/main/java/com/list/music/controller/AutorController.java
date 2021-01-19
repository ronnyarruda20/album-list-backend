package com.list.music.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.list.music.service.AutorService;

@RestController
public class AutorController {

	@Autowired
	private AutorService service;

	@GetMapping("/autors")
	public ResponseEntity<?> listaTodosAutores() {
		return ResponseEntity.of(service.findAll());
	}
}
