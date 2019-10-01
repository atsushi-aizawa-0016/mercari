package com.example.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.example.domain.Category;

@Repository
public class CategoryRepository {

	private static final RowMapper<Category> CATEGORY_ROW_MAPPER = (rs, i) -> {
		Category category = new Category();
		category.setId(rs.getInt("id"));
		category.setName(rs.getString("name"));
		category.setPrentId(rs.getInt("parent_id"));
		category.setNameAll(rs.getString("split_part"));
		return category;
	};
	
	@Autowired
	private NamedParameterJdbcTemplate template;
	
	public List<Category> selectParent(){
		String sql = "SELECT \n" + 
				"DISTINCT id,name,parent_id,SPLIT_PART(name_all,'/',1) \n" + 
				"FROM \n" + 
				"category \n" + 
				"WHERE id <= 10 \n" + 
				";";
		SqlParameterSource param = new MapSqlParameterSource();
		List<Category> parentList = template.query(sql, param, CATEGORY_ROW_MAPPER);
		return parentList;
	}
	
	public List<Category> selectChild(){
		String sql = "SELECT\n" + 
				"DISTINCT ON (parent_id,SPLIT_PART(name_all,'/',2)) \n" + 
				"id,name,parent_id,SPLIT_PART(name_all,'/',2) \n" + 
				"FROM \n" + 
				"category \n" + 
				"WHERE parent_id >= 11\n" + 
				";";
		SqlParameterSource param = new MapSqlParameterSource();
		List<Category> childList = template.query(sql, param, CATEGORY_ROW_MAPPER);
		return childList;
	}
	
	public List<Category> selectGrandChild(){
		String sql = "SELECT\n" + 
				"DISTINCT ON (parent_id,SPLIT_PART(name_all,'/',3)) \n" + 
				"id,name,parent_id,SPLIT_PART(name_all,'/',3) \n" + 
				"FROM \n" + 
				"category \n" + 
				"WHERE parent_id >= 11\n" + 
				";";
		SqlParameterSource param = new MapSqlParameterSource();
		List<Category> grandChildList = template.query(sql, param, CATEGORY_ROW_MAPPER);
		return grandChildList;
	}
	
	public List<Category> findByChildName(Integer parentId) {
		String sql = "SELECT id,name,parent_id,SPLIT_PART(name_all,'/',1) \n" + 
				"FROM category \n" + 
				"WHERE parent_id=:parentId\n" + 
				";";
		SqlParameterSource param = new MapSqlParameterSource().addValue("parentId", parentId);
		List<Category> childList = template.query(sql, param, CATEGORY_ROW_MAPPER);
		return childList;
	}
	
	public List<Category> findByGrandChildName(String name) {
		String sql = "SELECT id,name,parent_id,name_all split_part" + 
				" FROM category" + 
				" WHERE SPLIT_PART(name_all,'/',2) = :name"; 
		SqlParameterSource param = new MapSqlParameterSource().addValue("name", name);
		List<Category> grandChildList = template.query(sql, param, CATEGORY_ROW_MAPPER);
//		System.out.println("grandChildListのなかみ：" + grandChildList);
		return grandChildList;
	}
	public Category findByCategoryId(Integer categoryId) {
		//			System.out.println("categoryIdなにい？？：" + categoryId);
		String sql = "SELECT \n" + 
				"id,name,parent_id,name_all split_part \n" + 
				"from \n" + 
				"category \n" + 
				"WHERE id = :categoryId\n" + 
				";";
		SqlParameterSource param = new MapSqlParameterSource().addValue("categoryId", categoryId);
		Category categoryInfo = template.queryForObject(sql, param, CATEGORY_ROW_MAPPER);
		return categoryInfo;
	}
}
