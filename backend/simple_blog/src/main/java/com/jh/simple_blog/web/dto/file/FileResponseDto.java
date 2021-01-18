package com.jh.simple_blog.web.dto.file;

import com.jh.simple_blog.domain.file.File;

import lombok.Getter;

@Getter
public class FileResponseDto {
	private Long id;
	private String origFileName;
	private String fileName;
	private String filePath;

	public FileResponseDto(File entity) {
		this.id =entity.getId();
		this.origFileName =entity.getOrigFileName();
		this.fileName =entity.getFileName();
		this.filePath =entity.getFilePath();
	}
}
