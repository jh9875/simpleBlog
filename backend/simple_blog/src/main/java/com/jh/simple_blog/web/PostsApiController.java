package com.jh.simple_blog.web;

import java.util.List;

import com.jh.simple_blog.service.file.FileService;
import com.jh.simple_blog.service.posts.PostsService;
import com.jh.simple_blog.util.FileGenerator;
import com.jh.simple_blog.web.dto.file.FileSaveRequestDto;
import com.jh.simple_blog.web.dto.posts.PostsListResponseDto;
import com.jh.simple_blog.web.dto.posts.PostsResponseDto;
import com.jh.simple_blog.web.dto.posts.PostsSaveRequestDto;
import com.jh.simple_blog.web.dto.posts.PostsUpdateRequestDto;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class PostsApiController {
	private final PostsService postsService;
	private final FileService fileService;

	@PostMapping("/api/v1/{author}/posts")
	public Long save(@PathVariable String author,
		@RequestPart(value = "key") PostsSaveRequestDto requestDto,
		@RequestPart(value = "file") MultipartFile file) {

		FileGenerator fileGenerator =new FileGenerator(file);
		FileSaveRequestDto fileSaveRequestDto =fileGenerator.getFileSaveRequestDto();	
		Long fileId =null;
		if(fileSaveRequestDto !=null)
			fileId =fileService.saveFile(fileSaveRequestDto);

		return postsService.save(requestDto, fileId);
	}

	@PutMapping("/api/v1/{author}/posts/{id}")
	public Long update(@PathVariable String author, @PathVariable Long id,
		@RequestPart(value = "key") PostsUpdateRequestDto requestDto,
		@RequestPart(value = "file") MultipartFile file) {

		FileGenerator fileGenerator =new FileGenerator(file);
		FileSaveRequestDto fileSaveRequestDto =fileGenerator.getFileSaveRequestDto();	
		Long fileId =null;
		if(fileSaveRequestDto !=null)
			fileId =fileService.saveFile(fileSaveRequestDto);

		return postsService.update(id, requestDto, fileId);
    }

	@DeleteMapping("/api/v1/{author}/posts/{id}")
	public Long delete(@PathVariable String author, @PathVariable Long id) {
		postsService.delete(id);
		return id;
	}

	@GetMapping("/api/v1/posts/{id}")
	public PostsResponseDto findById(@PathVariable Long id) {
		return postsService.findById(id);
	}

	@GetMapping("/api/v1/posts/list")
    public List<PostsListResponseDto> findAll() {
        return postsService.findAllDesc();
    }
}
