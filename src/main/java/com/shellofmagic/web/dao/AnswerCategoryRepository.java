package com.shellofmagic.web.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AnswerCategoryRepository extends JpaRepository<AnswerCategoryDto, Integer> {
	
	public List<AnswerCategoryDto> findByAnswerId(Integer answerId);
	
	long count();
	
	@Query("SELECT COUNT(*) FROM answer_category WHERE category_id = :categId")
    long getCountByCateg(@Param("categId")Integer categId);
	
	@Query("SELECT ac FROM answer_category ac WHERE category_id = :categId")
	List<AnswerCategoryDto> getAllByCateg(@Param("categId")Integer categId, Pageable pageable);
	
}
