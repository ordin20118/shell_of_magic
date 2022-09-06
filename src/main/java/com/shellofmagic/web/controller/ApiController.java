package com.shellofmagic.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.shellofmagic.web.controller.param.AnswerParam;
import com.shellofmagic.web.dao.AnswerDto;
import com.shellofmagic.web.dao.CategoryDto;
import com.shellofmagic.web.dao.CategoryRepository;
import com.shellofmagic.web.service.ManageService;

@RestController
public class ApiController {
	
	@Autowired
	ManageService manageService;
	
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor={Exception.class})
	@PostMapping("/inputAnswer")
	public String inputAnswer(@RequestBody AnswerParam answerParam) {		
		try {			
			System.out.println("[inputAnswer()]" + answerParam);			
			manageService.saveAnswer(answerParam);			
			return "success";			
		} catch(Exception e) {
			throw e;
		}
	}
	
	@ResponseBody
	@PostMapping("/inputCategory")
	public String inputCategory(@RequestBody  CategoryDto categParam) throws Exception {		
		try {			
			System.out.println("[inputCategory()]" + categParam);
			if(categParam.getName() == null || categParam.getName().trim().equals("")) {
				throw new Exception("No name");						
			}
			// save category info
			manageService.saveCategory(categParam);
			return "success";
			
		} catch(Exception e) {
			throw e;
		}
	}

}
