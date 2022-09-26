package com.shellofmagic.web.dao;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.CreationTimestamp;

import lombok.Data;

@Data
@Entity(name="play_log")
public class PlayLogDto {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer	id;
	private Integer	categoryId;
	private Integer	answerId;
	private String 	question;
	private Integer	like;
	@CreationTimestamp
	private Date	regDate;

}