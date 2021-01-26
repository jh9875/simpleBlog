package com.jh.simple_blog.web.dto.posts;

import com.jh.simple_blog.domain.file.File;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PostsUpdateRequestDto {
	private String title;
	private String content;
	private File file;

	@Builder
	public PostsUpdateRequestDto(String title, String content, File file) {
		this.title = title;
		this.content = content;
		this.file =file;
	}

	public void setFile(File file) {
		this.file =file;
	}
}
