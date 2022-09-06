package com.shellofmagic.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shellofmagic.web.dao.AnswerDto;
import com.shellofmagic.web.service.ManageService;

@RestController
public class TestController {
	
	@GetMapping("/test")
	public String test() {		
		return "test controller";
	
	}

}
