package com.shellofmagic.web.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayLogRepository extends JpaRepository<PlayLogDto, Integer> {
	
}
