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
@RequestMapping("page")
public class SvcUiController {
	
	@Autowired
	ManageService manageService;
	
	@Autowired
	WebService webService;
	
	@GetMapping(value = {"/", "/home"})
	public String index(Model model) {		
		return "/svc/index";
	}
	
}