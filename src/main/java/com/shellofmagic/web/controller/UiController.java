package com.shellofmagic.web.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.shellofmagic.web.dao.AnswerDto;
import com.shellofmagic.web.dao.CategoryDto;
import com.shellofmagic.web.service.ManageService;
import com.shellofmagic.web.service.WebService;

@Controller
public class UiController {
	
	@Autowired
	ManageService manageService;
	
	@Autowired
	WebService webService;
	
	@GetMapping(value = {"/", "/home"})
	public String index(Model model) {
		
		System.out.println(LocalDateTime.now());
		
		List<AnswerDto> answerList = manageService.getAll();
		System.out.println(answerList);
		return "index";
	}
	
	@GetMapping(value = {"/inputAnswer"})
	public String inputAnswer(Model model) {
		List<CategoryDto> allCategory = manageService.getAllCategory();
		model.addAttribute("categoryList", allCategory);
		return "input_answer";
	}	
	
	@GetMapping(value = {"/inputCategory"})
	public String inputCategory(Model model) {
		return "input_category";
	}
	
	@GetMapping(value = {"/answerList"})
	public String answerList(Model model, @PageableDefault(size=10)Pageable pageable) {
		Page<AnswerDto> answers = webService.getAnswerList(pageable);
		model.addAttribute("answers", answers);
		System.out.println(answers);
		System.out.println(answers.getContent());
		return "list_answer";
	}
}
