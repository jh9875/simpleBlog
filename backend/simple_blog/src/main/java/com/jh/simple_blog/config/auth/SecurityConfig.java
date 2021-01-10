package com.jh.simple_blog.config.auth;

import com.jh.simple_blog.domain.user.Role;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{

	private final CustomOAuth2UserService customOAuth2UserService;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.csrf().disable()
			.headers().frameOptions().disable()
			.and()
				.authorizeRequests()									//URL별 권한 관리를 설정하는 옵션의 시작점.
				.antMatchers("/", "/posts/save", "/login/with", "/css/**", "/image/**", "/js/**", "/h2-console/**").permitAll()	//열람 권한?
				.antMatchers("/api/v1/**").hasRole(Role.USER.name())	//api/v1/** 는 USER권한을 가진 사람만 가능.
				.anyRequest().authenticated()
			.and()
				.logout()												//로그아웃 기능에 대한 여러 설정 진입점.
					.logoutSuccessUrl("/")								//로그아웃 시 /로 이동.
			.and()
				.oauth2Login()											//로그인 기능에 대한 여러 가지 설정 진입점.
					.userInfoEndpoint()									//OAuth2 로그인 성공 이후 사용자 정보를 가져올 때의 설정들을 담당.
						.userService(customOAuth2UserService);	
	}
 }
