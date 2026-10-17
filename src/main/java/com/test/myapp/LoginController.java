package com.test.myapp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/login")
public class LoginController {

	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

	@GetMapping("/login.do")
	public ModelAndView login(@RequestParam(required = false) String error,
			@RequestParam(required = false) String logout,
			@RequestParam(required = false) String session,
			@RequestParam(required = false) String multi) {
		logger.info("/login.do");
		ModelAndView model = new ModelAndView();

		if (error != null) {
			model.addObject("msg", "아이디와 비밀번호를 확인하세요.");
		}
		if (logout != null) {
			model.addObject("msg", "로그아웃 되었습니다.");
		}
		if (session != null) {
			model.addObject("msg", "세션이 만료되었습니다.");
		}
		if (multi != null) {
			model.addObject("msg", "다른 사용자가 로그인 하였습니다.");
		}

		model.setViewName("login");

		return model;
	}

	// 로그인 처리(POST /login/loginCheck.do)는 Spring Security 필터가 담당

}
