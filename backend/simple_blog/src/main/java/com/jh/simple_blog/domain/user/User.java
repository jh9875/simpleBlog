package com.jh.simple_blog.domain.user;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.jh.simple_blog.domain.BaseTimeEntity;
import com.jh.simple_blog.domain.posts.Posts;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity
public class User extends BaseTimeEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private String name;
	
	@Column(nullable = false)
	private String email;

	@Column(unique = true)
	private String author;

	@Column
	private String picture;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private Role role;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
	private List<Posts> posts =new ArrayList<>();

	@Builder
	public User(String name, String email, String picture, Role role) {
		this.name =name;
		this.email =email;
		this.picture =picture;
		this.role =role;
	}

	public User update(String name, String author, String picture) {
		this.name =name;
		this.author =author;
		this.picture =picture;

		return this;
	}

	public String getRoleKey() {
		return this.role.getKey();
	}

}
