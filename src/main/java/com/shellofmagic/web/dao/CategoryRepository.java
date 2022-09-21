package com.shellofmagic.web.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<CategoryDto, Long> {
	
	public List<CategoryDto> findById(Integer categoryId);
	

}
