package com.jh.simple_blog.domain.posts;

import java.util.List;

import com.jh.simple_blog.domain.user.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PostsRepository extends JpaRepository<Posts, Long> {
	@Query("SELECT p FROM Posts p ORDER BY p.id DESC")
	List<Posts> findAllDesc();

	@Query("SELECT p FROM Posts p WHERE user_id= :userId ORDER BY p.id DESC")
	List<Posts> findByUserIdDesc(@Param("userId") Long userId);
	
	List<Posts> findByUser(User user);
}
