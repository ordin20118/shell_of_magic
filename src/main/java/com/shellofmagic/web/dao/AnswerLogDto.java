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

import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Entity(name="answer_log")
public class AnswerLogDto {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Column(nullable = true, length = 50)
	private String	ip;
	
	@Column(nullable = true, length = 100)
	private String	client;
	
	@Column(nullable = false)
	Integer category;
	
	@Column(nullable = false)
	String question;
	
	@Column(nullable = false)
	Integer answerId;
	
	@CreationTimestamp
	private Date	regDate;

}
