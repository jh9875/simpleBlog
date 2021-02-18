package com.jh.simple_blog.web;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jh.simple_blog.domain.user.Role;
import com.jh.simple_blog.domain.user.User;
import com.jh.simple_blog.domain.user.UserRepository;
import com.jh.simple_blog.web.dto.user.UserUpdateRequestDto;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserApiControllerTest {

	@LocalServerPort
	private int port;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private WebApplicationContext context;

	private MockMvc mvc;

	@Before
	public void setup() {
		mvc = MockMvcBuilders.webAppContextSetup(context).apply(springSecurity()).build();
	}

	@After
	public void tearDown() throws Exception {
		userRepository.deleteAll();
	}

	@Test
	public void 유저_세팅_테스트() throws Exception {
		//given
		User savedUser =userRepository.save(User.builder()
			.name("name")
			.email("email")
			.picture("https://unsplash.com/photos/0wIHsm2_1fc")
			.role(Role.USER)
			.build());
		Long updatedId =savedUser.getId();
		String updatedName ="updatedname";
		String email ="email";
		String updatedAuthor ="updatedAuthor";
		String updatedPicture ="https://unsplash.com/photos/TEemXOpR3cQ";

		UserUpdateRequestDto requestDto =UserUpdateRequestDto.builder()
			.name(updatedName)
			.email(email)
			.author(updatedAuthor)
			.picture(updatedPicture)
			.build();

		String url ="http://localhost:" + port + "/api/v1/me/setting";

		//when
		mvc.perform(put(url)
			.contentType(MediaType.APPLICATION_JSON_UTF8)
			.content(new ObjectMapper().writeValueAsString(requestDto)))
			.andExpect(status().isOk());

		//then
		User user =userRepository.findById(updatedId).orElseThrow();
		assertThat(user.getId()).isEqualTo(updatedId);
		assertThat(user.getName()).isEqualTo(updatedName);
		assertThat(user.getEmail()).isEqualTo(email);
		assertThat(user.getAuthor()).isEqualTo(updatedAuthor);
		assertThat(user.getPicture()).isEqualTo(updatedPicture);
	}
}
