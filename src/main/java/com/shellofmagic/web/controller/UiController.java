package com.shellofmagic.web.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.shellofmagic.web.dao.AnswerDto;
import com.shellofmagic.web.service.AnswerService;

@Controller
public class UiController {
	
	@Autowired
	AnswerService answerService;
	
	@GetMapping(value = {"/", "/home"})
	public String index(Model model) {
		
		System.out.println(LocalDateTime.now());
		
		List<AnswerDto> answerList = answerService.getAll();
		System.out.println(answerList);
		return "index";
	}
	
}
