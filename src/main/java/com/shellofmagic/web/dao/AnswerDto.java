package com.shellofmagic.web.dao;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.CreationTimestamp;

import lombok.Data;

@Data
@Entity(name="answer")
public class AnswerDto {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer answerId;
	@Column(nullable = false, length = 500)
	private String	answer;
	//@CreationTimestamp
	private Date	regDate;

}
