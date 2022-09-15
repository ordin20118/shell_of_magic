package com.shellofmagic.web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.shellofmagic.web.controller.param.AnswerParam;
import com.shellofmagic.web.dao.AnswerCategoryDto;
import com.shellofmagic.web.dao.AnswerCategoryRepository;
import com.shellofmagic.web.dao.AnswerDto;
import com.shellofmagic.web.dao.AnswerRepository;
import com.shellofmagic.web.dao.CategoryDto;
import com.shellofmagic.web.dao.CategoryRepository;

@Service
public class WebService {
	
	@Autowired
	private AnswerRepository answerRepoitory;
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	private AnswerCategoryRepository answerCategoryRepository;
	
	
	public Page<AnswerDto> getAnswerList(Pageable pageable) {
		Page<AnswerDto> resData = answerRepoitory.findAll(pageable);
		return resData;
	}
	
	
}
