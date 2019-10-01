package com.example.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.example.domain.Item;

/**
 * 商品操作をするリポジトリー.
 * 
 * @author atsushi
 *
 */
@Repository
public class ItemRepository {
	
	private static final RowMapper<Item> ITEM_ROW_MAPPER = (rs, i) -> {
		Item item = new Item();
		item.setId(rs.getInt("i_id"));
		item.setName(rs.getString("i_name"));
		item.setItemConditionId(rs.getInt("i_item_condition_id"));
		item.setCategoryId(rs.getInt("i_category_id"));
		item.setBrandName(rs.getString("i_brand_name"));
		item.setPrice(rs.getDouble("i_price"));
		item.setShipping(rs.getInt("i_shipping"));
		item.setItemDescription(rs.getString("i_item_description"));
		item.setNameAll(rs.getString("c_name_all"));
		return item;
	};
	
	@Autowired
	private NamedParameterJdbcTemplate template;

//	/**
//	 * 商品一覧を表示する.
//	 * 
//	 * @return 商品一覧
//	 */
//	public List<Item> findAll() {
//		String sql = "SELECT \n" + 
//				"i.id i_id,\n" + 
//				"i.name i_name,\n" + 
//				"i.item_condition_id i_item_condition_id,\n" + 
//				"i.category_id i_category_id,\n" + 
//				"i.brand_name i_brand_name,\n" + 
//				"i.price i_price,\n" + 
//				"i.shipping i_shipping,\n" + 
//				"i.item_description i_item_description,\n" + 
//				"c.name_all c_name_all \n" + 
//				"FROM items i INNER JOIN category c \n" + 
//				"ON i.category_id = c.id\n" + 
//				"WHERE i.id <= 50\n" + 
//				";";
//		SqlParameterSource param = new MapSqlParameterSource();
//		List<Item> itemList = template.query(sql, param, ITEM_ROW_MAPPER);
//		return itemList;
//	}
	
	public List<Item> findList(Integer pageNum) {
		int selectNum = 30 * (pageNum - 1);
		String sql = "SELECT\n" + 
				"i.id i_id,\n" + 
				"i.name i_name,\n" + 
				"i.item_condition_id i_item_condition_id,\n" + 
				"i.category_id i_category_id,\n" + 
				"i.brand_name i_brand_name,\n" + 
				"i.price i_price,\n" + 
				"i.shipping i_shipping,\n" + 
				"i.item_description i_item_description,\n" + 
				"c.name_all c_name_all\n" + 
				"FROM items i INNER JOIN category c\n" + 
				"ON i.category_id = c.id \n" + 
				"ORDER BY i.id \n" + 
				"LIMIT 30 OFFSET " + selectNum + 
				";";
		SqlParameterSource param = new MapSqlParameterSource();
		List<Item> itemList = template.query(sql, param, ITEM_ROW_MAPPER);
		return itemList;
	}
	
	public List<Item> findByParentCategory(String parentName,Integer pageNum){
		int selectNum = 30 * (pageNum - 1);
		String sql = "SELECT\n" + 
				"i.id i_id,\n" + 
				"i.name i_name,\n" + 
				"i.item_condition_id i_item_condition_id,\n" + 
				"i.category_id i_category_id,\n" + 
				"i.brand_name i_brand_name,\n" + 
				"i.price i_price,\n" + 
				"i.shipping i_shipping,\n" + 
				"i.item_description i_item_description,\n" + 
				"c.name_all c_name_all  \n" + 
				"FROM items i INNER JOIN category c\n" + 
				"ON i.category_id = c.id\n" + 
				"WHERE SPLIT_PART(c.name_all,'/',1) = :parentName\n" + 
				"ORDER BY i.id \n" + 
				"LIMIT 30 OFFSET " + selectNum + 
				";";
		SqlParameterSource param = new MapSqlParameterSource().addValue("parentName", parentName);
		List<Item> itemParentList = template.query(sql, param, ITEM_ROW_MAPPER);
		return itemParentList;
	}
	
	public List<Item> findByParentChildCategory(String parentName,Integer pageNum){
		int selectNum = 30 * (pageNum - 1);
		String sql = "SELECT\n" + 
				"i.id i_id,\n" + 
				"i.name i_name,\n" + 
				"i.item_condition_id i_item_condition_id,\n" + 
				"i.category_id i_category_id,\n" + 
				"i.brand_name i_brand_name,\n" + 
				"i.price i_price,\n" + 
				"i.shipping i_shipping,\n" + 
				"i.item_description i_item_description,\n" + 
				"c.name_all c_name_all  \n" + 
				"FROM items i INNER JOIN category c\n" + 
				"ON i.category_id = c.id\n" + 
				"WHERE c.name_all LIKE :parentName \n" + 
				"ORDER BY i.id \n" + 
				"LIMIT 30 OFFSET " + selectNum + 
				";";
		SqlParameterSource param = new MapSqlParameterSource().addValue("parentName", parentName + "%");
//		System.out.println("sqlのなかみ：" + sql);
		List<Item> itemParentList = template.query(sql, param, ITEM_ROW_MAPPER);
		return itemParentList;
	}
	
	public List<Item> findByParentChildGrandChildCategory(String parentName,Integer pageNum){
		int selectNum = 30 * (pageNum - 1);
		String sql = "SELECT\n" + 
				"i.id i_id,\n" + 
				"i.name i_name,\n" + 
				"i.item_condition_id i_item_condition_id,\n" + 
				"i.category_id i_category_id,\n" + 
				"i.brand_name i_brand_name,\n" + 
				"i.price i_price,\n" + 
				"i.shipping i_shipping,\n" + 
				"i.item_description i_item_description,\n" + 
				"c.name_all c_name_all  \n" + 
				"FROM items i INNER JOIN category c\n" + 
				"ON i.category_id = c.id\n" + 
				"WHERE c.name_all = :parentName \n" + 
				"ORDER BY i.id \n" + 
				"LIMIT 30 OFFSET " + selectNum + 
				";";
		SqlParameterSource param = new MapSqlParameterSource().addValue("parentName", parentName);
//		System.out.println("sqlのなかみ：" + sql);
		List<Item> itemParentList = template.query(sql, param, ITEM_ROW_MAPPER);
		return itemParentList;
	}
	
	//category側にお引越し
//	public List<Item> findByCategoryId(Integer categoryId) {
//		System.out.println("categoryIdなにい？？：" + categoryId);
//		String sql = "SELECT \n" + 
//				"id,name,parent_id,name_all \n" + 
//				"from \n" + 
//				"category \n" + 
//				"WHERE id = :categoryId\n" + 
//				";";
//		SqlParameterSource param = new MapSqlParameterSource().addValue("categoryId", categoryId);
//		List<Item> itemList = template.query(sql, param, ITEM_ROW_MAPPER);
//		return itemList;
//	}
}
