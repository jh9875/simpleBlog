package com.jh.simple_blog.web.dto.posts;

import com.jh.simple_blog.domain.file.File;
import com.jh.simple_blog.domain.posts.Posts;
import com.jh.simple_blog.domain.user.User;

import lombok.Getter;

@Getter
public class PostsResponseDto {
	private Long id;
	private String title;
	private String content;
	private File file;
	private User user;

	public PostsResponseDto(Posts entity) {
		this.id = entity.getId();
		this.title = entity.getTitle();
		this.content = entity.getContent();
		this.file =entity.getFile();
		this.user =entity.getUser();
	}

}
