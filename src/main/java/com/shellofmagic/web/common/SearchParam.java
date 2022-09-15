package com.shellofmagic.web.common;

import lombok.Data;

@Data
public class SearchParam {

	private String 	keyword;
	private Integer nowPage;
	private Integer	perPage;
	private	Integer	pageSize;
	
	public SearchParam() {
		this.pageSize = 1;
		this.perPage = 10;
		this.pageSize = 10;
	}
	
	public int getOffset() {
		return (nowPage - 1) * perPage;
	}
}
