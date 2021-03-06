package com.list.music.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jlefebure.spring.boot.minio.MinioException;
import com.list.music.dto.AlbumDto;
import com.list.music.message.ResponseMessage;
import com.list.music.model.Album;
import com.list.music.pagination.PageableResponse;
import com.list.music.service.AlbumService;

@RestController
@RequestMapping("/album")
public class AlbumController {

	@Autowired
	private AlbumService service;

	@GetMapping("/all")
	public ResponseEntity<?> listaTodosAlbuns() {
		return ResponseEntity.of(service.findAll());
	}

	@GetMapping("/allPaginate")
	public ResponseEntity<?> listaTodosAlbuns(
			@RequestParam(value = "pageNumber", required = false, defaultValue = "0") int page,
			@RequestParam(value = "pageSize", required = false, defaultValue = "5") int size) {
		return ResponseEntity.ok(service.findAll(page, size));
//		return new PageableResponse<AlbumDto>(service.findAll(page, size));
	}

	@GetMapping("/list")
	public PageableResponse<Album> search(@RequestParam(value = "searchTerm", required = false) String searchTerm,
			@RequestParam(value = "pageNumber", required = false, defaultValue = "0") int page,
			@RequestParam(value = "pageSize", required = false, defaultValue = "5") int size,
			@RequestParam(value = "sort", required = false, defaultValue = "ASC") String sort) {
		return new PageableResponse<Album>(service.search(searchTerm, page, size, sort));

	}

	@PostMapping("/save")
	public ResponseEntity<ResponseMessage> saveAlbum(@RequestBody AlbumDto albumDto) {
		try {
			this.service.saveDto(albumDto);
		} catch (MinioException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		String message = "Salvo com sucesso";
		return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
	}

	@PostMapping("/delete")
	public ResponseEntity<ResponseMessage> deleteAlbum(@RequestParam("id") String id) {
		String message = "";
		try {
			service.removeAlbum(Integer.valueOf(id));
		} catch (MinioException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		message = "successfully deleted";
		return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
	}
	
	@GetMapping("/teste")
	public String teste() {
		return "deu certo";

	}
}
