package com.test.myapp;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/main")
public class MainController {

	private static final Logger logger = LoggerFactory.getLogger(MainController.class);

	@GetMapping("/main.do")
	public ModelAndView main(Locale locale) {
		logger.info("/main.do");
		DateTimeFormatter formatter = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.LONG).withLocale(locale);
		String formattedDate = ZonedDateTime.now().format(formatter);
		ModelAndView model = new ModelAndView();
		model.addObject("serverTime", formattedDate);
		model.setViewName("main");
		return model;
	}

	@GetMapping("/showUser.do")
	public ModelAndView showUser() {
		logger.info("/showUser.do");
		ModelAndView model = new ModelAndView();
		model.setViewName("showUser");
		return model;
	}

}
