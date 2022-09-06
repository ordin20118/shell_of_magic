package com.shellofmagic.web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shellofmagic.web.dao.AnswerDto;
import com.shellofmagic.web.dao.AnswerRepository;

@Service
public class AnswerService {
	
	@Autowired
	private AnswerRepository answerRepoitory;
	
	public AnswerDto save(AnswerDto answer) {
		answerRepoitory.save(answer);
		return answer;
	}
	
	public List<AnswerDto> getAll() {
		return answerRepoitory.findAll();
	}

}
