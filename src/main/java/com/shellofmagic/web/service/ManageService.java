package com.shellofmagic.web.service;

import java.util.List;
import java.util.Optional;

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