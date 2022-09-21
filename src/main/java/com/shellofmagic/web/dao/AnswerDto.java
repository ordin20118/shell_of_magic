package com.shellofmagic.web.dao;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import org.hibernate.annotations.CreationTimestamp;

import lombok.Data;

@Data
@Entity(name="answer")
public class AnswerDto {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	
	@Column(nullable = false, length = 500)
	private String	answer;
	
	@CreationTimestamp
	private Date	regDate;

	@OneToMany(mappedBy="answerId", cascade=CascadeType.PERSIST, fetch = FetchType.LAZY)
	//@JoinColumn(name="id", referencedColumnName = "answerId")
	List<AnswerCategoryDto> categories;

}
