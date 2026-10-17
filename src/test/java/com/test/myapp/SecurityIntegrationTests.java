package com.test.myapp;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.formLogin;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.authenticated;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.unauthenticated;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
class SecurityIntegrationTests {

	@Autowired
	private MockMvc mvc;

	@Test
	void loginPageIsAccessibleAnonymously() throws Exception {
		mvc.perform(get("/login/login.do"))
			.andExpect(status().isOk())
			.andExpect(forwardedUrl("/WEB-INF/views/login.jsp"));
	}

	@Test
	void protectedPageRedirectsToLogin() throws Exception {
		mvc.perform(get("/main/main.do"))
			.andExpect(status().is3xxRedirection())
			.andExpect(redirectedUrl("/login/login.do"));
	}

	@Test
	void loginSucceedsWithValidCredentials() throws Exception {
		mvc.perform(formLogin("/login/loginCheck.do").userParameter("user_id").passwordParam("user_pwd")
				.user("dev").password("1234"))
			.andExpect(authenticated().withRoles("DEV"))
			.andExpect(redirectedUrl("/main/main.do"));
	}

	@Test
	void loginFailsWithInvalidCredentials() throws Exception {
		mvc.perform(formLogin("/login/loginCheck.do").userParameter("user_id").passwordParam("user_pwd")
				.user("dev").password("wrong"))
			.andExpect(unauthenticated())
			.andExpect(redirectedUrl("/login/login.do?error"));
	}

	@Test
	void anonymousRoleUserCannotAccessMain() throws Exception {
		mvc.perform(get("/main/main.do").with(user("anony").roles("ANONYMOUS")))
			.andExpect(status().isForbidden());
	}

	@Test
	void adminPageRequiresAdminOrDev() throws Exception {
		mvc.perform(get("/admin/x.do").with(user("user").roles("USER")))
			.andExpect(status().isForbidden());
		mvc.perform(get("/dev/x.do").with(user("admin").roles("ADMIN")))
			.andExpect(status().isForbidden());
	}

	@Test
	void logoutRedirectsToLoginPage() throws Exception {
		mvc.perform(post("/login/logout.do").with(user("user").roles("USER")).with(csrf()))
			.andExpect(redirectedUrl("/login/login.do?logout"));
	}

}
