package com.jh.simple_blog.util;

import java.io.File;

import com.jh.simple_blog.web.dto.file.FileSaveRequestDto;

import org.springframework.web.multipart.MultipartFile;

import lombok.Getter;

@Getter
public class FileGenerator {
	
	private FileSaveRequestDto fileSaveRequestDto;

	public FileGenerator(MultipartFile file) {
		if(!file.getOriginalFilename().equals("")) {
			try {
				String origFileName =file.getOriginalFilename();
				String fileName =new MD5Generator(origFileName).toString();
				String savePath =System.getProperty("user.dir") +"\\src/main/resources/static/posts-image";
				if(!new File(savePath).exists()) {
					try {
						new File(savePath).mkdir();
					}
					catch(Exception e) {
						e.getStackTrace();
					}
				}
				String filePath =savePath + "\\" + fileName;
				file.transferTo(new File(filePath));
	
				fileSaveRequestDto =FileSaveRequestDto.builder().origFileName(origFileName)
					.fileName(fileName).filePath(filePath).build();
			}
			catch(Exception e) {
				e.printStackTrace();
			}
		}	

	}

}
