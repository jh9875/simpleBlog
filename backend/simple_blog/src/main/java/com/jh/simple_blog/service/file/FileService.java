package com.jh.simple_blog.service.file;

import com.jh.simple_blog.domain.file.File;
import com.jh.simple_blog.domain.file.FileRepository;
import com.jh.simple_blog.web.dto.file.FileResponseDto;
import com.jh.simple_blog.web.dto.file.FileSaveRequestDto;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class FileService {
	private final FileRepository fileRepository;

	@Transactional
	public Long saveFile(FileSaveRequestDto fileDto) {
		return fileRepository.save(fileDto.toEntity()).getId();
	}

	@Transactional(readOnly = true)
	public FileResponseDto findById(Long id) {
		File file =fileRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("해당 파일이 없습니다. id = " + id));
		return new FileResponseDto(file);
	}
}
