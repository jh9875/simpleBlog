package com.jh.simple_blog.web.dto.file;

import com.jh.simple_blog.domain.file.File;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class FileSaveRequestDto {
	private String origFileName;
	private String fileName;
	private String filePath;

	@Builder
	public FileSaveRequestDto(String origFileName, String fileName, String filePath) {
		this.origFileName =origFileName;
		this.fileName =fileName;
		this.filePath =filePath;
	}
	
	public File toEntity() {
		return File.builder()
				.origFileName(origFileName)
				.fileName(fileName)
				.filePath(filePath)
				.build();
	}
}
