package kr.co.sloop.study.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Handles requests for the application home page.
 */
@Controller
@RequestMapping("/sample")
public class StudyController {
	
	private static final Logger logger = LoggerFactory.getLogger(StudyController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping
	public String home(Locale locale, Model model) {
		logger.info("Welcome Sampleeeeeeee! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		return "sample/sample"; // String으로 반환할 때 jsp 파일이 저장된 위치부터 지정해야 함. return "{폴더명}/{jsp파일명}";
	}
	
}
