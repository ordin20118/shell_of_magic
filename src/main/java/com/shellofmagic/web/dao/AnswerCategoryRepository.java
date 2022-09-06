package com.shellofmagic.web.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnswerCategoryRepository extends JpaRepository<AnswerCategoryDto, Long> {
	
	public List<AnswerCategoryDto> findByAnswerId(Integer answerId);
	

}
