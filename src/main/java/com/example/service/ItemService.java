package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.domain.Item;
import com.example.repository.ItemRepository;

/**
 * 商品サービス.
 * 
 * @author atsushi
 *
 */
@Service
public class ItemService {

	@Autowired
	private ItemRepository itemRepository;
	
	/**
	 * 商品一覧を取得.
	 * 
	 * @return 商品一覧
	 */
//	public List<Item> findAll(){
//		return itemRepository.findAll();
//	}
	
	public List<Item> findList(Integer pageNum){
		return itemRepository.findList(pageNum);
	}
	
	public List<Item> findByParentCategory(String category,Integer pageNum){
		String parentName = category;
		return itemRepository.findByParentCategory(parentName,pageNum);
	}
	public List<Item> findByParentChildCategory(String parentChildCategory,Integer pageNum){
//		System.out.println("parentChildCategory;のなかみ：" + parentChildCategory);
		String parentName = parentChildCategory;
		return itemRepository.findByParentChildCategory(parentName,pageNum);
	}
	public List<Item> findByParentChildGrandChilCategory(String paraChilGraChilCategory,Integer pageNum){
//		System.out.println("parentChildCategory;のなかみ：" + paraChilGraChilCategory);
		String parentName = paraChilGraChilCategory;
		return itemRepository.findByParentChildCategory(parentName,pageNum);
	}
	
//	public List<Item> findByCategoryId(Integer categoryId) {
//		return itemRepository.findByCategoryId(categoryId);
//	}
}
