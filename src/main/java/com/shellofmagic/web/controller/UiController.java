package com.shellofmagic.web.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.shellofmagic.web.dao.AnswerDto;
import com.shellofmagic.web.dao.CategoryDto;
import com.shellofmagic.web.service.ManageService;

@Controller
public class UiController {
	
	@Autowired
	ManageService manageService;
	
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
	public String answerList(Model model) {
		return "list_answer";
	}
}
