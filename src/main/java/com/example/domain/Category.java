package com.example.domain;

import java.util.Map;

public class Category {

	private Integer id;
	private String name;
	private Integer prentId;
	private String nameAll;
	private Map<String, String> parentNameMap;
	
	public Map<String, String> getParentNameMap() {
		return parentNameMap;
	}
	public void setParentNameMap(Map<String, String> parentNameMap) {
		this.parentNameMap = parentNameMap;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getPrentId() {
		return prentId;
	}
	public void setPrentId(Integer prentId) {
		this.prentId = prentId;
	}
	public String getNameAll() {
		return nameAll;
	}
	public void setNameAll(String nameAll) {
		this.nameAll = nameAll;
	}
	@Override
	public String toString() {
		return "Category [id=" + id + ", name=" + name + ", prentId=" + prentId + ", nameAll=" + nameAll
				+ ", parentNameMap=" + parentNameMap + "]";
	}
	

	
	
}
