package com.shellofmagic.web.dao;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import org.hibernate.annotations.CreationTimestamp;

import lombok.Data;

@Data
@Entity(name="answer_category")
public class AnswerCategoryDto {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer	id;
	private Integer answerId;
	@CreationTimestamp
	private Date	regDate;
	
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "categoryId", referencedColumnName = "id")
	private CategoryDto category;
	
}
