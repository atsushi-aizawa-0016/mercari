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
}
