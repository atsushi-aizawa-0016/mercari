package com.example.form;

public class ItemForm {

	private String pageNum;
	
	
	public Integer getIntPageNum() {
		return Integer.parseInt(pageNum);
	}
	public String getPageNum() {
		return pageNum;
	}

	public void setPageNum(String pageNum) {
		this.pageNum = pageNum;
	}

	@Override
	public String toString() {
		return "ItemForm [pageNum=" + pageNum + "]";
	}
	
	
}
