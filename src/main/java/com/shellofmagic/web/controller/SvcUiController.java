package com.shellofmagic.web.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
	
	@GetMapping(value = {"", "/", "/home"})
	public String index(Model model) {
		
		List<CategoryDto> allCategory = manageService.getAllCategory();
		model.addAttribute("categoryList", allCategory);
		
		return "svc/index";
	}

	@GetMapping(value = {"/play"})
	public String play(@RequestParam(name="") Integer categId, Model model) throws ParseException {
		CategoryDto categ = webService.getCategory(categId);
		model.addAttribute("category", categ);
		
		SimpleDateFormat sdf = new SimpleDateFormat("HH");
		sdf.setTimeZone(TimeZone.getTimeZone("Asia/Seoul"));
		Date now = new Date();
		String nowHour = sdf.format(now);
		model.addAttribute("nowHour", Integer.parseInt(nowHour));
		return "svc/play";
	}
	
}
