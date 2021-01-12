package com.jh.simple_blog.service.user;

import com.jh.simple_blog.domain.user.UserRepository;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UserService {
	private final UserRepository userRepository;
	
}
