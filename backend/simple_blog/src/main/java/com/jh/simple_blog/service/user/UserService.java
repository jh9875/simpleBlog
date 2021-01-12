package com.jh.simple_blog.service.user;

import com.jh.simple_blog.domain.user.User;
import com.jh.simple_blog.domain.user.UserRepository;
import com.jh.simple_blog.web.dto.user.UserResponseDto;
import com.jh.simple_blog.web.dto.user.UserUpdateRequestDto;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UserService {
	private final UserRepository userRepository;

	@Transactional
	public Long update(UserUpdateRequestDto requestDto) {
		User user = userRepository.findByEmail(requestDto.getEmail())
				.orElseThrow(() -> new IllegalArgumentException("해당 유저가 없습니다. email =" + requestDto.getEmail()));

		return user.update(requestDto.getName(), requestDto.getPicture()).getId();
	}

	// @Transactional
	// public void delete (String email) {
	// 	User user =userRepository.findByEmail(email).orElseThrow(() ->new
	// 		IllegalArgumentException("해당 유저가 없습니다. email=" +email));

	// 		userRepository.delete(user);
	// }

	@Transactional(readOnly = true)
	public UserResponseDto findByEmail(String email) {
		User entity = userRepository.findByEmail(email)
				.orElseThrow(() -> new IllegalArgumentException("해당 유저가 없습니다. email =" + email));

		return new UserResponseDto(entity);
	}
}
