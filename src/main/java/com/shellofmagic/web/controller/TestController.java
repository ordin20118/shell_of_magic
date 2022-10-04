package com.shellofmagic.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.shellofmagic.web.dao.CategoryDto;
import com.shellofmagic.web.service.ManageService;

@Controller
public class TestController {
	
	@Autowired
	ManageService manageService;
	
	@GetMapping("/")
	public String index(Model model) {

		List<CategoryDto> allCategory = manageService.getAllCategory();
		model.addAttribute("categoryList", allCategory);
		
		return "svc/index";
		//return "redirect:page/home";
	}

}
