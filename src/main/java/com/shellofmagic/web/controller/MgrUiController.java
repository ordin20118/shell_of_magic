package com.shellofmagic.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.shellofmagic.web.dao.AnswerDto;
import com.shellofmagic.web.dao.CategoryDto;
import com.shellofmagic.web.service.ManageService;
import com.shellofmagic.web.service.WebService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("mgr")
public class MgrUiController {
	
	@Autowired
	ManageService manageService;
	
	@Autowired
	WebService webService;
	
	@GetMapping(value = {"/", "/home"})
	public String index(Model model) {		
		return "/manage/index";
	}
	
	@GetMapping(value = {"/inputAnswer"})
	public String inputAnswer(Model model) {
		List<CategoryDto> allCategory = manageService.getAllCategory();
		model.addAttribute("categoryList", allCategory);
		return "/manage/input_answer";
	}	
	
	@GetMapping(value = {"/inputCategory"})
	public String inputCategory(Model model) {
		return "/manage/input_category";
	}
	
	@GetMapping(value = {"/answerList"})
	public String answerList(Model model, @PageableDefault(size=10)Pageable pageable) {
		Page<AnswerDto> answers = webService.getAnswerList(pageable);
		model.addAttribute("answers", answers);
		System.out.println(answers);
		System.out.println(answers.getContent());
		return "/manage/list_answer";
	}

	@GetMapping(value = {"/modifyAnswer/{answerId}"})
	public String modifyAnswer(@PathVariable Integer answerId, Model model) {
		
		// set category
		List<CategoryDto> allCategory = manageService.getAllCategory();
		model.addAttribute("categoryList", allCategory);
		
		// set answer
		AnswerDto answer = webService.getAnswer(answerId);
		model.addAttribute("answer", answer);
		
		return "/manage/modify_answer";
	}
}
