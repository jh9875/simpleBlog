package com.jh.simple_blog.web;

import com.jh.simple_blog.service.user.UserService;
import com.jh.simple_blog.web.dto.user.UserUpdateRequestDto;

import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class UserApiController {
	private final UserService userService;

	@PutMapping("/api/v1/me/setting")
	public Long update(@RequestBody UserUpdateRequestDto requestDto) {
		return userService.update(requestDto);
	}

	@PutMapping("/api/v1/me/register")
	public Long register(@RequestBody UserUpdateRequestDto requestDto) {
		return userService.update(requestDto);
	}

	// @DeleteMapping("/api/v1/user/setting{email}")
	// public Long delete(@PathVariable String email) {
	// 	UserResponseDto user = userService.findByEmail(email);
	// 	userService.delete(user.getEmail());

	// 	return user.getId();
	// }

	
}
