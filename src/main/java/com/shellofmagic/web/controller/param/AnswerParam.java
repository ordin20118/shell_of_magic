package com.shellofmagic.web.controller.param;

import java.util.List;

import com.shellofmagic.web.dao.CategoryDto;

import lombok.Data;

@Data
public class AnswerParam {
	
	private String question;
	private String answer;
	private List<CategoryDto> categoryList;

}
