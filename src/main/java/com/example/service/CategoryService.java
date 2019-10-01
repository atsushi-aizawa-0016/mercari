package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.domain.Category;
import com.example.repository.CategoryRepository;

@Service
public class CategoryService {

	@Autowired
	private CategoryRepository categoryRepository;
	
	public List<Category> selectParent(){
		return categoryRepository.selectParent();
	}
	
	public List<Category> selectChild(){
		return categoryRepository.selectChild();
	}
	
	public List<Category> selectGrandChild(){
		return categoryRepository.selectGrandChild();
	}
	
//	public List<Category> findByChildName(String parentId){
//		return categoryRepository.findByChildName(parentId);
//	}
	
	public String getPulldownData(String id) {
		StringBuilder s = new StringBuilder();
		s.append("[");
		
//		System.out.println("idの値：" + id);
		Integer parentId = Integer.parseInt(id);
		
		List<Category> childList = categoryRepository.findByChildName(parentId);
		
		for (Category category : childList) {
			s.append("{\"");
	        s.append("value");
	        s.append("\"");
	        s.append(":\"");
	        s.append(category.getName());
	        s.append("\",");
	        s.append("\"");
	        s.append("label");
	        s.append("\"");
	        s.append(":\"");
	        s.append(category.getName());
	        s.append("\"}");
	        s.append(",");
		}
		
		s.deleteCharAt(s.lastIndexOf(","));
		s.append("]");
		
//		System.out.println("getPulldownDateメソッド終了直前の変数sbの中身：" + s.toString());
		
		return s.toString();
	}
	public String getPulldownData2(String id) {
		StringBuilder s = new StringBuilder();
		s.append("[");
		
		String name = id;
//		System.out.println("nameの値：" + name);
		
		List<Category> grandChildList = categoryRepository.findByGrandChildName(name);
		
		for (Category category : grandChildList) {
			s.append("{\"");
	        s.append("value");
	        s.append("\"");
	        s.append(":\"");
	        s.append(category.getName());
	        s.append("\",");
	        s.append("\"");
	        s.append("label");
	        s.append("\"");
	        s.append(":\"");
	        s.append(category.getName());
	        s.append("\"}");
	        s.append(",");
		}
		
		s.deleteCharAt(s.lastIndexOf(","));
		s.append("]");
		
//		System.out.println("getPulldownDateメソッド終了直前の変数sbの中身：" + s.toString());
		
		return s.toString();
	}
	
	public Category findByCategoryId(Integer categoryId) {
		return categoryRepository.findByCategoryId(categoryId);
	}
}
