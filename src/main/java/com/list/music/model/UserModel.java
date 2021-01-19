package com.list.music.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "USER_MODEL")
public class UserModel {

	@Id
	private Integer Id;

	@Column(name = "USERNAME")
	private String userName;
	private String password;
}
