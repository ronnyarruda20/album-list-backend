package com.list.music.service;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.list.music.jwt.UserResponse;
import com.list.music.model.UserModel;

@Service
public class JwtUserDetailsService implements UserDetailsService {

	@Autowired
	private UserService service;

	@Override
	public UserResponse loadUserByUsername(String userName) throws UsernameNotFoundException {

		Optional<UserModel> userModel = service.findByName(userName);
		if (!userModel.isPresent()) {
			throw new UsernameNotFoundException("User not found with name: " + userName);
		}
		return new UserResponse(userModel.get().getUserName(), userModel.get().getPassword(), new ArrayList<>()).loadUser(userModel.get());
		
	}

}