package com.list.music.jwt;


import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import com.list.music.model.UserModel;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserResponse extends User {



	private static final long serialVersionUID = -457953005883218098L;

	public UserResponse(String username, String password, Collection<? extends GrantedAuthority> authorities) {
		super(username, password, authorities);
	}
	
	private Integer Id;
	private String token;
	

	public UserResponse loadUser(UserModel userModel) {
		setId(userModel.getId());
		return this;
	}

}
