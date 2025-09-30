package com.test.myapp;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/main")
public class MainController {
	
	private static final Logger logger = LoggerFactory.getLogger(MainController.class);
	
	@RequestMapping(value = "/main.do")
	public ModelAndView main(HttpServletRequest request, HttpServletResponse response, Locale locale) {
		logger.info("/main.do");
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		String formattedDate = dateFormat.format(date);
		ModelAndView model = new ModelAndView();
		model.addObject("serverTime", formattedDate );
		model.setViewName("main");
		return model;
	}
	
	@RequestMapping(value = "/showUser.do")
	public ModelAndView showUser(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		logger.info("/showUser.do");
		ModelAndView model = new ModelAndView();
		model.setViewName("showUser");
		return model;
		
	}
	
}
