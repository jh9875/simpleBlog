package com.jh.simple_blog.web;

import com.jh.simple_blog.service.file.FileService;

import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class FileApiController {
	private final FileService fileService;

	
}
