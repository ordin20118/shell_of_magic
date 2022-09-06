package com.shellofmagic.web.dao;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.CreationTimestamp;

import lombok.Data;

@Data
@Entity(name="answer_category")
public class AnswerCategoryDto {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer	cid;
	private Integer answerId;
	private Integer categoryId;
	@CreationTimestamp
	private Date	regDate;

}
