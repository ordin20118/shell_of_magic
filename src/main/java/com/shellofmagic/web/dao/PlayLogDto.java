package com.shellofmagic.web.dao;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
@Entity(name="play_log")
public class PlayLogDto {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer	id;
	
	@JsonProperty("category_id")
	@Column(nullable = false)
	private Integer	categoryId;
	
	@JsonProperty("answer_id")
	@Column(nullable = false)
	private Integer	answerId;
	
	@Column(nullable = true, length = 300)
	private String 	question;
	
	@JsonProperty("is_like")
	@Column(nullable = false)
	private Integer	isLike;
	
	@CreationTimestamp
	@Column(nullable = false)
	private Date	regDate;

}
