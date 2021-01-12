package com.jh.simple_blog.web.dto.user;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserUpdateRequestDto {
	private String name;
	private String email;
	private String picture;
	
	@Builder
	public UserUpdateRequestDto(String name, String email, String picture) {
		this.name =name;
		this.email =email;
		this.picture =picture;
	}
}
