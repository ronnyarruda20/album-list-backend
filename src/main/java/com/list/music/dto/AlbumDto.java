package com.list.music.dto;

import java.io.Serializable;

import org.springframework.web.multipart.MultipartFile;

import com.list.music.model.Album;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;



@Getter
@Setter
@NoArgsConstructor
@ToString(callSuper = false)
public class AlbumDto implements Serializable {
	
	private static final long serialVersionUID = 3865032520016920975L;
	
	public AlbumDto(Album album) {
		setId(album.getId());
		setNome(album.getNome());
		setImagem(album.getImagem());
		setAutor(new AutorDto(album.getAutor()));
	}
	
	private Integer id;

	private String nome;
	
	private String imagem;

	private AutorDto autor;
	
	private MultipartFile file;
	
}

