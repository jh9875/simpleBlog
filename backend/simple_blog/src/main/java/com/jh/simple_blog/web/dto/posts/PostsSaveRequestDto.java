package com.jh.simple_blog.web.dto.posts;

import com.jh.simple_blog.domain.file.File;
import com.jh.simple_blog.domain.posts.Posts;
import com.jh.simple_blog.domain.user.User;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PostsSaveRequestDto {
	private String title;
	private String content;
	private String email;
	private File file;
	private User user;

	@Builder
	public PostsSaveRequestDto(String title, String content, String email) {
		this.title = title;
		this.content = content;
		this.email =email;
	}

	public void setUser(User user) {
		this.user =user;
	}

	public Posts toEntity() {
		return Posts.builder()
				.title(title)
				.content(content)
				.user(user)
				.build();
	}
}
