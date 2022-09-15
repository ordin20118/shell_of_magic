package com.shellofmagic.web.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface AnswerRepository extends JpaRepository<AnswerDto, Long> {
	
	public List<AnswerDto> findByAnswerId(Integer id);
	
	public Page<AnswerDto> findAll(Pageable pageable);
	
	public long count();
	

}
