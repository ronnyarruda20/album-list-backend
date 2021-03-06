package com.list.music.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Transient;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Entity
@ToString(callSuper = false)
public class Album implements Serializable {

	private static final long serialVersionUID = 1451934009957215044L;

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="album_sequence")
	@SequenceGenerator(name="album_sequence", sequenceName="ALB_SEQ", allocationSize = 1)
	private Integer id;

	private String nome;

	private String imagem;

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
	@JoinColumn(name = "AUTOR_ID")
	private Autor autor;

	@Transient
	private String file;
}
