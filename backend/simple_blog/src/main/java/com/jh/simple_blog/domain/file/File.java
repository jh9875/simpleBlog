package com.jh.simple_blog.domain.file;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import com.jh.simple_blog.domain.posts.Posts;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity
public class File {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private String origFileName;

	@Column(nullable = false)
	private String fileName;

	@Column(nullable = false)
	private String filePath;

	@OneToOne(mappedBy = "file")
	private Posts posts;

	@Builder
	public File(String origFileName, String fileName, String filePath) {
		this.origFileName =origFileName;
		this.fileName =fileName;
		this.filePath =filePath;
	}
}
