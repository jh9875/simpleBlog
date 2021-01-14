package com.jh.simple_blog.web;

import java.util.List;

import com.jh.simple_blog.service.posts.PostsService;
import com.jh.simple_blog.web.dto.posts.PostsListResponseDto;
import com.jh.simple_blog.web.dto.posts.PostsResponseDto;
import com.jh.simple_blog.web.dto.posts.PostsSaveRequestDto;
import com.jh.simple_blog.web.dto.posts.PostsUpdateRequestDto;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class PostsApiController {
	private final PostsService postsService;

	@PostMapping("/api/v1/{author}/posts")
	public Long save(@PathVariable String author, @RequestBody PostsSaveRequestDto requestDto) {
		System.out.println("author: " +author);
		return postsService.save(requestDto);
	}

	@PutMapping("/api/v1/posts/{id}")
    public Long update(@PathVariable Long id, @RequestBody PostsUpdateRequestDto requestDto) {
        return postsService.update(id, requestDto);
    }

	@DeleteMapping("/api/v1/posts/{id}")
	public Long delete(@PathVariable Long id) {
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
