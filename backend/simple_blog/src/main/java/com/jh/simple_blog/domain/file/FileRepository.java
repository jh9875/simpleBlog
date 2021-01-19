package com.jh.simple_blog.domain.file;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface FileRepository extends JpaRepository<File, Long> {
	// Optional<File> findByFileName(String fileName);
}
