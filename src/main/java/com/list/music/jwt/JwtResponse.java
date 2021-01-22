package com.list.music.jwt;

import java.io.Serializable;

import lombok.Getter;

@Getter
public class JwtResponse implements Serializable {

	private static final long serialVersionUID = -3073540338463001004L;

	private final String jwttoken;

	public JwtResponse(String jwttoken) {
		this.jwttoken = jwttoken;
	}

}