package com.shellofmagic.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.shellofmagic.web.common.ObjectMapperInstance;
import com.shellofmagic.web.controller.param.AnswerParam;
import com.shellofmagic.web.dao.AnswerDto;
import com.shellofmagic.web.dao.CategoryDto;
import com.shellofmagic.web.service.ManageService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api")
public class ApiController {
	
	@Autowired
	ManageService manageService;
	
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor={Exception.class})
	@PostMapping("/answers")
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
	@PostMapping("/categories")
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
	
	@GetMapping("/answers/{answerId}")
	public ResponseEntity<String> getAnswerList(@PathVariable Integer answerId) {
		
		String resString = "{}";
		HttpStatus resStatus = HttpStatus.OK;	
		ObjectMapper mapper = ObjectMapperInstance.getInstance().getMapper();		
		
		try {
			AnswerDto answer = manageService.getAnswer(answerId);
			resString = mapper.writeValueAsString(answer);
		} catch (JsonProcessingException e) {
			resStatus = HttpStatus.INTERNAL_SERVER_ERROR;
			log.error("[Get Answer Error]:"+e.getMessage());
		}
		
		return new ResponseEntity<String>(resString, resStatus);	
	}
	

	@GetMapping("/answers")
	public ResponseEntity<String> getAnswerList(@PageableDefault(size=10)Pageable pageable) {
		
		String resString = "{}";
		HttpStatus resStatus = HttpStatus.OK;	
		ObjectMapper mapper = ObjectMapperInstance.getInstance().getMapper();		
		
		try {
			Page<AnswerDto> answers = manageService.getAllAnswers(pageable);
			resString = mapper.writeValueAsString(answers);
		} catch (JsonProcessingException e) {
			resStatus = HttpStatus.INTERNAL_SERVER_ERROR;
			log.error("[Get Answer List Error]:"+e.getMessage());
		}
		
		return new ResponseEntity<String>(resString, resStatus);	
	}

}