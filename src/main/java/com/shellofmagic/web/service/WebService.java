package com.shellofmagic.web.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.shellofmagic.web.controller.MgrUiController;
import com.shellofmagic.web.dao.AnswerCategoryDto;
import com.shellofmagic.web.dao.AnswerCategoryRepository;
import com.shellofmagic.web.dao.AnswerDto;
import com.shellofmagic.web.dao.AnswerRepository;
import com.shellofmagic.web.dao.CategoryRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
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
	
	public AnswerDto getAnswer(Integer id) {
		Optional<AnswerDto> resData = answerRepoitory.findById(id);
		if(resData.isPresent()) {
			return resData.get();
		} else {
			return null;
		}
	}
	
	public AnswerDto getRandomAnswer(Integer categId) {
		
		log.info("[getRandomAnswer]:" + categId);
		
		long total = 0;
		
		if(categId == null) {
			total = answerCategoryRepository.count();
		} else {
			total = answerCategoryRepository.getCountByCateg(categId);
		}
		
		log.info("[TOTAL]:" + total);
		
		int idx = (int)(Math.random() * total);
		
		log.info("[Random IDX]:" + idx);
		
		Page<AnswerCategoryDto> acPaging = answerCategoryRepository.findAll(PageRequest.of(idx, 1));
		List<AnswerCategoryDto> acList = acPaging.getContent();
		
		if(acList != null && acList.size() > 0) {
			return answerRepoitory.findById(acList.get(0).getAnswerId()).orElse(null);	
		} else {
			return new AnswerDto();
		}
		
	}
		
}
