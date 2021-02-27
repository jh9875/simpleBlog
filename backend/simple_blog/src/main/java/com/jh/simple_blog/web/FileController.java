package com.jh.simple_blog.web;

import java.io.IOException;
import java.io.InputStream;

import com.jh.simple_blog.domain.file.FileRepository;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class FileController {
	private final FileRepository fileRepository;

	
}
