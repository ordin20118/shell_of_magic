package com.shellofmagic.web.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnswerRepository extends JpaRepository<AnswerDto, Long> {
	
	public List<AnswerDto> findByAnswerId(Integer id);
	

}
