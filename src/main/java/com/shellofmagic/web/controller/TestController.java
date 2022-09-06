package com.shellofmagic.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shellofmagic.web.dao.AnswerDto;
import com.shellofmagic.web.service.AnswerService;

@RestController
public class TestController {
	
	@Autowired
	AnswerService answerService;
	
	@GetMapping("/test")
	public String test() {
		
		AnswerDto answer = new AnswerDto();
		answer.setAnswer("아무것도 먹지마.");
		
		answerService.save(answer);
		return "test controller";
	
	}

}
