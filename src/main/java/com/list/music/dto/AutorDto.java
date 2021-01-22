package com.list.music.dto;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import com.list.music.model.Album;
import com.list.music.model.Autor;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString(callSuper = false)
public class AutorDto implements Serializable {
	
	private static final long serialVersionUID = 959753156837413513L;
	
	public AutorDto(Autor autor) {
		setId(autor.getId());
		setNome(autor.getNome());
		setAlbums(autor.getAlbums());
	}

	private Integer id;

	private String nome;
	
	private Set<Album> albums = new HashSet<>();

}
