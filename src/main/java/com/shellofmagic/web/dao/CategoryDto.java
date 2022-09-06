package com.shellofmagic.web.dao;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.CreationTimestamp;

import lombok.Data;

@Data
@Entity(name="category")
public class CategoryDto {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer	categoryId;
	private String 	name;
	private Date	regDate;
	@CreationTimestamp
	private Date	updateDate;

}
