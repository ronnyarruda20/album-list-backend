package com.list.music.jwt;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JwtRequest implements Serializable {

	private static final long serialVersionUID = -4072595351213584976L;
	
	private String username;
	private String password;

	public JwtRequest() {
	}

	public JwtRequest(String username, String password) {
		this.setUsername(username);
		this.setPassword(password);
	}

}
