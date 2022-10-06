package com.shellofmagic.web.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.shellofmagic.web.controller.param.AnswerParam;
import com.shellofmagic.web.dao.AnswerCategoryDto;
import com.shellofmagic.web.dao.AnswerCategoryRepository;
import com.shellofmagic.web.dao.AnswerDto;
import com.shellofmagic.web.dao.AnswerRepository;
import com.shellofmagic.web.dao.CategoryDto;
import com.shellofmagic.web.dao.CategoryRepository;

import jdk.internal.org.jline.utils.Log;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ManageService {
	
	@Autowired
	private AnswerRepository answerRepoitory;
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	private AnswerCategoryRepository answerCategoryRepository;
	
	public AnswerDto saveAnswer(AnswerParam answerParam) {
		
		AnswerDto answer = new AnswerDto();
		answer.setAnswer(answerParam.getAnswer());
		answerRepoitory.save(answer);
		
		for(int i=0; i<answerParam.getCategoryList().size(); i++) {
			CategoryDto categ = answerParam.getCategoryList().get(i);
			AnswerCategoryDto ac = new AnswerCategoryDto();
			ac.setAnswerId(answer.getId());
			ac.setCategory(categ);
			answerCategoryRepository.save(ac);
		}
		
		return answer;
	}
	
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor={Exception.class})
	public AnswerDto modifyAnswer(AnswerParam answerParam) {
		
		AnswerDto answer = answerRepoitory.findById(answerParam.getAnswerId()).get();
		answer.setAnswer(answerParam.getAnswer());
		answerRepoitory.save(answer);
		
		List<AnswerCategoryDto> answerCategList = answerCategoryRepository.findByAnswerId(answerParam.getAnswerId());
		
		List<AnswerCategoryDto> passList = new ArrayList<>();
		
		for(int i=0; i<answerParam.getCategoryList().size(); i++) {
			CategoryDto categ = answerParam.getCategoryList().get(i);			
			for(int j=0; j<answerCategList.size(); j++) {
				AnswerCategoryDto ac = answerCategList.get(j);
				if(ac.getCategory().getId() == categ.getId()) {
					log.info("[PASS]:"+ac);
					passList.add(ac);
					answerCategList.remove(j);
					break;
				}
			}			
		}
		
		for(CategoryDto c : answerParam.getCategoryList()) {
			
			boolean isFind = false;
			for(AnswerCategoryDto ac : passList) {
				if(ac.getCategory().getId() == c.getId()) {					
					isFind = true;
				}
			}
			
			if(!isFind) {
				AnswerCategoryDto newAc = new AnswerCategoryDto();
				newAc.setAnswerId(answer.getId());
				newAc.setCategory(c);
				log.info("[INSERT]:"+newAc);
				answerCategoryRepository.save(newAc);	
			}			
		}
		
		for(AnswerCategoryDto ac : answerCategList) {
			log.info("[DELETE]:"+ac);
			answerCategoryRepository.delete(ac);
		}
		
		return answer;
	}
	
	public AnswerDto getAnswer(Integer answerId) {
		Optional<AnswerDto> opAnswer = answerRepoitory.findById(answerId);
		if(opAnswer.isPresent()) {
			return opAnswer.get();
		} else {
			return null;	
		}		
	}
	
	public Page<AnswerDto> getAllAnswers(Pageable pageable) {
		return answerRepoitory.findAll(pageable);
	}

	public CategoryDto saveCategory(CategoryDto category) {
		categoryRepository.save(category);
		return category;
	}
	
	public List<CategoryDto> getAllCategory() {
		return categoryRepository.findAll();
	}
	
	
}
