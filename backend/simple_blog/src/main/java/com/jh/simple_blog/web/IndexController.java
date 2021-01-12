package com.jh.simple_blog.web;

import java.util.List;

import com.jh.simple_blog.config.auth.LoginUser;
import com.jh.simple_blog.config.auth.dto.SessionUser;
import com.jh.simple_blog.service.posts.PostsService;
import com.jh.simple_blog.web.dto.PostsListResponseDto;
import com.jh.simple_blog.web.dto.PostsResponseDto;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
@Controller
public class IndexController {
	private final PostsService postsService;
	
	@GetMapping("/")
	public String index(Model model, @LoginUser SessionUser user) {
		model.addAttribute("posts", postsService.findAllDesc());

		if(user !=null)
			model.addAttribute("usr", user);
			
		return "index";
	}

	@GetMapping("/posts/save")
	public String postsSave(Model model, @LoginUser SessionUser user) {
		
		if(user !=null)
			model.addAttribute("usr", user);

		return "posts-save";
	}

	@GetMapping("/posts/update/{id}")
    public String postsUpdate(@PathVariable Long id, Model model, @LoginUser SessionUser user) {
		PostsResponseDto dto = postsService.findById(id);
		model.addAttribute("post", dto);
		
		if(user !=null)
			model.addAttribute("usr", user);

        return "posts-update";
	}

	@GetMapping("/user")
	public String user(Model model, @LoginUser SessionUser user) {
		List<PostsListResponseDto> userPosts =postsService.findByUser(user.getEmail());
		model.addAttribute("userPosts", userPosts);

		if(user !=null)
			model.addAttribute("usr", user);

		return "user";
	}
	
	@GetMapping("/sign/in")
	public String signin() {

		return "sign-in";
	}

	@GetMapping(value="/sign/out")
	public String signout() {
		return "sign-out";
	}
	
}
