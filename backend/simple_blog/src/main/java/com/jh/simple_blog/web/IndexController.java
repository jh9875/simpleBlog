package com.jh.simple_blog.web;

import javax.mail.Session;
import javax.servlet.http.HttpSession;

import com.jh.simple_blog.config.auth.LoginUser;
import com.jh.simple_blog.config.auth.dto.SessionUser;
import com.jh.simple_blog.service.posts.PostsService;
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
			model.addAttribute("usrName", user.getName());

		return "index";
	}

	@GetMapping("/posts/save")
	public String postsSave() {
		return "posts-save";
	}

	@GetMapping("/posts/update/{id}")
    public String postsUpdate(@PathVariable Long id, Model model) {
        PostsResponseDto dto = postsService.findById(id);
        model.addAttribute("post", dto);

        return "posts-update";
    }
}
