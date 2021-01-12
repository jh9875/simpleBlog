package com.jh.simple_blog.web.dto.posts;

import java.time.LocalDateTime;

import com.jh.simple_blog.domain.posts.Posts;
import com.jh.simple_blog.domain.user.User;

import lombok.Getter;

@Getter
public class PostsListResponseDto {
    private Long id;
	private String title;
	private User user;
    private LocalDateTime modifiedDate;

    public PostsListResponseDto(Posts entity) {
        this.id = entity.getId();
		this.title = entity.getTitle();
		this.user =entity.getUser();
        this.modifiedDate = entity.getModifiedDate();
    }
}