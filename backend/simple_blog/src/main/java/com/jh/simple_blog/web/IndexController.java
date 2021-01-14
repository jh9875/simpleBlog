package com.jh.simple_blog.web;

import java.util.List;

import com.jh.simple_blog.config.auth.LoginUser;
import com.jh.simple_blog.config.auth.dto.SessionUser;
import com.jh.simple_blog.service.posts.PostsService;
import com.jh.simple_blog.service.user.UserService;
import com.jh.simple_blog.web.dto.posts.PostsListResponseDto;
import com.jh.simple_blog.web.dto.posts.PostsResponseDto;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
@Controller
public class IndexController {
	private final PostsService postsService;
	private final UserService userService;
	
	@GetMapping("/")
	public String index(Model model, @LoginUser SessionUser user) {	
		model.addAttribute("posts", postsService.findAllDesc());

		if(user !=null)
			model.addAttribute("usr", userService.findByEmail(user.getEmail()));
			
		return "index";
	}

	@GetMapping(value ={"/posts/save"})
	public String postsSave(Model model, @LoginUser SessionUser user) {

		if(user !=null)
			model.addAttribute("usr", userService.findByEmail(user.getEmail()));

		return "posts-save";
	}

	@GetMapping(value ={"/{author}/posts/view/{id}"})
    public String postsView(@PathVariable String author, @PathVariable Long id, Model model, @LoginUser SessionUser user) {
		PostsResponseDto dto = postsService.findById(id);
		model.addAttribute("post", dto);
		
		if(user !=null)
			model.addAttribute("usr", userService.findByEmail(user.getEmail()));

        return "posts-view";
	}

	@GetMapping(value ={"/{author}/posts/update/{id}"})
    public String postsUpdate(@PathVariable String author, @PathVariable Long id, Model model, @LoginUser SessionUser user) {
		PostsResponseDto dto = postsService.findById(id);
		model.addAttribute("post", dto);
		
		if(user !=null)
			model.addAttribute("usr", userService.findByEmail(user.getEmail()));

        return "posts-update";
	}

	@GetMapping("/me")
	public String user(Model model, @LoginUser SessionUser user) {
		List<PostsListResponseDto> userPosts =postsService.findByUserEmail(user.getEmail());
		model.addAttribute("userPosts", userPosts);

		if(user !=null)
			model.addAttribute("usr", userService.findByEmail(user.getEmail()));

		return "user";
	}

	@GetMapping("me/setting")
	public String setting(Model model, @LoginUser SessionUser user) {
		if(user !=null)
			model.addAttribute("usr", userService.findByEmail(user.getEmail()));

		return "user-setting";
	}

	@GetMapping("me/register")
	public String register(Model model, @LoginUser SessionUser user) {
		if(user !=null)
			model.addAttribute("usr", userService.findByEmail(user.getEmail()));

		return "user-register";
	}
	
	@GetMapping("/sign/in")
	public String signIn() {
		return "sign-in";
	}

	@GetMapping(value="/sign/out")
	public String signOut() {
		return "sign-out";
	}
}
