package com.jh.simple_blog.domain.posts;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import com.jh.simple_blog.domain.BaseTimeEntity;
import com.jh.simple_blog.domain.file.File;
import com.jh.simple_blog.domain.user.User;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity
public class Posts extends BaseTimeEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(length = 500, nullable = false)
	private String title;

	@Column(columnDefinition = "TEXT", nullable = false)
	private String content;

	@OneToOne(fetch = FetchType.LAZY, mappedBy = "posts")
	private File file;

	@ManyToOne
	@JoinColumn(name="user_id")
	private User user;

	@Builder
	public Posts(String title, String content, File file, User user) {
		this.title = title;
		this.content = content;
		this.file =file;
		this.user =user;
	}

	public void update(String title, String content, File file) {
		this.title = title;
		this.content = content;
		this.file =file;
	}
}
