package com.test.myapp;

import jakarta.servlet.DispatcherType;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.session.HttpSessionEventPublisher;

/**
 * 기존 security-context.xml 대체
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {

	private static final String REMEMBER_ME_COOKIE = "remember_me";

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http
			.authorizeHttpRequests(auth -> auth
				// JSP 포워드 및 에러 페이지 디스패치는 허용 (요청 자체는 이미 인가됨)
				.dispatcherTypeMatchers(DispatcherType.FORWARD, DispatcherType.ERROR).permitAll()
				// 정적 resource (전체 허용)
				.requestMatchers("/resources/**", "/*.ico").permitAll()
				// 로그인 페이지 (미 로그인 상태)
				.requestMatchers("/login/login.do").anonymous()
				// 개발자 페이지 (개발자 허용)
				.requestMatchers("/dev/**").hasRole("DEV")
				// 관리자 페이지 (관리자 허용)
				.requestMatchers("/admin/**").hasAnyRole("DEV", "ADMIN")
				// 그 외 나머지 페이지 (로그인 사용자 허용)
				.anyRequest().hasAnyRole("DEV", "ADMIN", "USER"))

			// 로그인 설정
			.formLogin(form -> form
				.loginPage("/login/login.do")
				.usernameParameter("user_id")
				.passwordParameter("user_pwd")
				.loginProcessingUrl("/login/loginCheck.do")
				.defaultSuccessUrl("/main/main.do", true)
				.failureUrl("/login/login.do?error"))

			// 로그아웃 설정
			.logout(logout -> logout
				.logoutUrl("/login/logout.do")
				.logoutSuccessUrl("/login/login.do?logout")
				.invalidateHttpSession(true)
				.deleteCookies("JSESSIONID", REMEMBER_ME_COOKIE))

			// 유효기간 7일
			.rememberMe(remember -> remember
				.key("myapp-remember-me")
				.tokenValiditySeconds(604800)
				.rememberMeParameter("remember_me")
				.rememberMeCookieName(REMEMBER_ME_COOKIE))

			// 중복 로그인 설정
			.sessionManagement(session -> session
				.invalidSessionUrl("/login/login.do?session")
				.maximumSessions(1)
				.expiredUrl("/login/login.do?multi"));

		// CSRF(cross site request forgery) 는 기본 활성화

		return http.build();
	}

	// 계정별 권한 설정 ({noop}: 평문 비밀번호, 학습용)
	@Bean
	public UserDetailsService userDetailsService() {
		return new InMemoryUserDetailsManager(
				User.withUsername("dev").password("{noop}1234").roles("DEV").build(),
				User.withUsername("admin").password("{noop}1234").roles("ADMIN").build(),
				User.withUsername("user").password("{noop}1234").roles("USER").build(),
				User.withUsername("anony").password("{noop}1234").roles("ANONYMOUS").build());
	}

	// 중복 로그인 제어(maximumSessions)를 위한 세션 이벤트 전달
	@Bean
	public HttpSessionEventPublisher httpSessionEventPublisher() {
		return new HttpSessionEventPublisher();
	}

}
