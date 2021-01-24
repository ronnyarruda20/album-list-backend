package com.list.music.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Entity
@ToString(callSuper = false, exclude = "albums")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class Autor implements Serializable {

	private static final long serialVersionUID = 6665188981554899435L;

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="autor_sequence")
	@SequenceGenerator(name="autor_sequence", sequenceName="AUT_SEQ")
	private Integer id;

	private String nome;

	@JsonIgnore
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "autor")
	private Set<Album> albums = new HashSet<>();

}
