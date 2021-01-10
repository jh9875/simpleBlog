package com.jh.simple_blog.service.posts;

import java.util.List;
import java.util.stream.Collectors;

import com.jh.simple_blog.domain.posts.Posts;
import com.jh.simple_blog.domain.posts.PostsRepository;
import com.jh.simple_blog.domain.user.User;
import com.jh.simple_blog.domain.user.UserRepository;
import com.jh.simple_blog.web.dto.PostsListResponseDto;
import com.jh.simple_blog.web.dto.PostsResponseDto;
import com.jh.simple_blog.web.dto.PostsSaveRequestDto;
import com.jh.simple_blog.web.dto.PostsUpdateRequestDto;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class PostsService {
	private final PostsRepository postsRepository;
	private final UserRepository userRepository;

	@Transactional
	public Long save(PostsSaveRequestDto requestDto) {
		User userEntity =userRepository.findByEmail(requestDto.getEmail())
			.orElseThrow(() -> new IllegalArgumentException("잘못된 사용자입니다.. email =" + requestDto.getEmail()));
		requestDto.setUser(userEntity);

		return postsRepository.save(requestDto.toEntity()).getId();
	}

	@Transactional
	public Long update(Long id, PostsUpdateRequestDto requestDto) {
		Posts posts = postsRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id =" + id));

		posts.update(requestDto.getTitle(), requestDto.getContent());

		return id;
	}
	@Transactional
	public void delete (Long id) {
		Posts posts =postsRepository.findById(id).orElseThrow(() ->new
			IllegalArgumentException("해당 게시글이 없습니다. id=" +id));

			postsRepository.delete(posts);
	}
	
	@Transactional(readOnly = true)
	public PostsResponseDto findById(Long id) {
		Posts entity = postsRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id =" + id));

		return new PostsResponseDto(entity);
	}

	@Transactional(readOnly = true)
    public List<PostsListResponseDto> findAllDesc() {
        return postsRepository.findAllDesc().stream()
			.map(PostsListResponseDto::new)
			.collect(Collectors.toList());
    }
}
