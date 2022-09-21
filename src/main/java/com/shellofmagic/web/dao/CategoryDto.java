package com.shellofmagic.web.dao;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.LastModifiedDate;

import lombok.Data;

@Data
@Entity(name="category")
public class CategoryDto {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer	id;
	private String 	name;
	@CreationTimestamp
	private Date	regDate;
	@LastModifiedDate
	private Date	updateDate;

}
