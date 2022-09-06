package com.shellofmagic.web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
		
		// TODO: save category mapping info
		for(int i=0; i<answerParam.getCategoryList().size(); i++) {
			CategoryDto categ = answerParam.getCategoryList().get(i);
			AnswerCategoryDto ac = new AnswerCategoryDto();
			ac.setAnswerId(answer.getAnswerId());
			ac.setCategoryId(categ.getCategoryId());
			answerCategoryRepository.save(ac);
		}
		
		return answer;
	}
	
	public List<AnswerDto> getAll() {
		return answerRepoitory.findAll();
	}

	public CategoryDto saveCategory(CategoryDto category) {
		categoryRepository.save(category);
		return category;
	}
	
	public List<CategoryDto> getAllCategory() {
		return categoryRepository.findAll();
	}
	
	
}
