package com.jh.simple_blog.web.dto.user;

import com.jh.simple_blog.domain.user.User;

import lombok.Getter;

@Getter
public class UserResponseDto {
	private Long id;
	private String name;
	private String email;
	private String author;
	private String picture;
	

	public UserResponseDto(User entity) {
		this.id =entity.getId();
		this.name =entity.getName();
		this.email =entity.getEmail();
		this.author =entity.getAuthor();
		this.picture =entity.getPicture();
	}
}
