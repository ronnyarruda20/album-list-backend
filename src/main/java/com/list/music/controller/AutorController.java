package com.list.music.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.list.music.dto.AutorDto;
import com.list.music.message.ResponseMessage;
import com.list.music.service.AutorService;

@RestController
@RequestMapping("/autor")
public class AutorController {

	@Autowired
	private AutorService service;

	@GetMapping("/list")
	public ResponseEntity<?> listaTodosAutores() {
		return ResponseEntity.of(service.findAll());
	}
	
	@PostMapping("/save")
	public ResponseEntity<ResponseMessage> save(@RequestBody AutorDto autorDto) {
		this.service.save(autorDto);
		String message = "Salvo com sucesso";
		return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
	}
	
	@PostMapping("/delete")
	public ResponseEntity<ResponseMessage> delete(@RequestParam("id") String id) {
		service.remove(Integer.valueOf(id));
		String message = "Removido com sucesso";
		return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
	}
}
