package com.test.myapp;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/login")
public class LoginController {
	
	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

	@RequestMapping(value = "/login.do")
	public ModelAndView login(HttpServletRequest request, HttpServletResponse response) {
		logger.info("/login.do");
		ModelAndView model = new ModelAndView();
		
		String error = request.getParameter("error");
    	String logout = request.getParameter("logout");
    	String session = request.getParameter("session");
    	String multi = request.getParameter("multi");

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
	
	@RequestMapping(value = "/loginCheck.do")
	public ModelAndView loginCheck(HttpServletRequest request, HttpServletResponse response) {
		logger.info("/loginCheck.do");
		ModelAndView model = new ModelAndView();
		return model;
	}
	
}
