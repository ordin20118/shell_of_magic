package com.shellofmagic.web.controller.param;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.shellofmagic.web.dao.CategoryDto;

import lombok.Data;

@Data
public class AnswerParam {
	
	private Integer	answerId;
	private String 	question;
	private String 	answer;
	private List<CategoryDto> categoryList;

}
