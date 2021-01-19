package com.list.music.controller;

import java.io.IOException;
import java.io.InputStream;
import java.net.URLConnection;
import java.nio.file.Path;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.google.api.client.util.IOUtils;
import com.jlefebure.spring.boot.minio.MinioException;
import com.jlefebure.spring.boot.minio.MinioService;
import com.list.music.message.ResponseMessage;

import io.minio.messages.Item;

@RestController
@CrossOrigin
@RequestMapping("album/files")
public class TestController {

	@Autowired
	private MinioService minioService;

	@GetMapping
	public List<Item> testMinio() throws MinioException {
		return minioService.list();
	}

	@GetMapping("/{object}")
	public void getObject(@PathVariable("object") String object, HttpServletResponse response)
			throws MinioException, IOException {
		InputStream inputStream = minioService.get(Path.of(object));
		InputStreamResource inputStreamResource = new InputStreamResource(inputStream);

		// Set the content type and attachment header.
		response.addHeader("Content-disposition", "attachment;filename=" + object);
		response.setContentType(URLConnection.guessContentTypeFromName(object));

		// Copy the stream to the response's output stream.
		IOUtils.copy(inputStream, response.getOutputStream());
		response.flushBuffer();
	}

	  @PostMapping("/upload")
	  public ResponseEntity<ResponseMessage> uploadFile(@RequestParam("file") MultipartFile file) {
	    String message = "";
	    try {
	    	System.out.println( file.getInputStream() +"  - "+file.getOriginalFilename());

	      message = "Uploaded the file successfully: " + file.getOriginalFilename();
	      return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
	    } catch (Exception e) {
	      message = "Could not upload the file: " + file.getOriginalFilename() + "!";
	      return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
	    }
	  }

	@PostMapping
	public void addAttachement(@RequestParam("file") MultipartFile file) throws MinioException {
		Path path = Path.of(file.getOriginalFilename());
		try {
			System.out.println(file.getInputStream() + "  - " + file.getOriginalFilename());
//            minioService.upload(path, file.getInputStream(), file.getContentType());
		} catch (IOException e) {
			throw new IllegalStateException("The file cannot be read", e);
		}
	}
}