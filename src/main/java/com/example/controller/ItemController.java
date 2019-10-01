package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.domain.Category;
import com.example.domain.Item;
import com.example.service.CategoryService;
import com.example.service.ItemService;

@Controller
@RequestMapping("/item")
public class ItemController {

	@Autowired
	private ItemService itemService;
	@Autowired
	private CategoryService categoryService;
	

	//	@GetMapping("/index")
	//	public String index(Category category,Model model) {
	//		System.out.println("HTMLから飛んできたcategory：" + category);
	//		
	//		List<Category> parentCategories = categoryService.findByDistinctParent();
	//		List<String> parentNames = new ArrayList<>();
	//		Category categoryRecord = null;
	//		
	//		for (int i = 0; i < parentCategories.size() ; i++) {
	//			categoryRecord = parentCategories.get(i);
	//			String parantName = categoryRecord.getName();
	//			parentNames.add(parantName);
	//		}
	//		
	//		LinkedHashMap<String, String> parentNameMap = new LinkedHashMap<>();
	//		
	//		Integer key;
	//		for (int i = 0; i < parentNames.size() ; i++) {
	//			key = i;
	//			parentNameMap.put(key.toString(), parentNames.get(i));
	//		}
	//		category.setParentNameMap(parentNameMap);
	//		model.addAttribute("parentCategories", parentCategories);
	//		System.out.println("parentCategories-------" + parentCategories);
	//		return "list";
	//	}

	@GetMapping("/parentCategory/{parentCategory}")
	@ResponseBody
	public String refreshCategoryPulldown(@PathVariable("parentCategory") String id) {
		return categoryService.getPulldownData(id);
	}

	@GetMapping("/childCategory/{childCategory}")
	@ResponseBody
	public String refreshCategoryPulldown2(@PathVariable("childCategory") String id) {
		return categoryService.getPulldownData2(id);
	}

	@RequestMapping("/selectPage")
	public String selectPage(Integer pageNum,String category,Integer categoryId,Model model) {

		List<Category> parentCategories = categoryService.selectParent();
		model.addAttribute("parentCategories", parentCategories);

		//最初のページのみページナンバーを指定
		if (pageNum == null) {
			pageNum = 1;
		}

		//ページナンバーをスコープに格納
		model.addAttribute("pageNum",pageNum);

		if (category == null) {
			List<Item> itemList = itemService.findList(pageNum);			
			model.addAttribute("itemList",itemList);
		}else {
			//実験、あとで消す
			//					System.out.println("categoryはなに？" + category);
			Category categoryInfo = categoryService.findByCategoryId(categoryId);
			//					System.out.println("categoryIdで検索した中身：" + categoryInfo);
			String[] str = categoryInfo.getNameAll().split("/");
			//					System.out.println("str[0]:" + str[0] + ",str[1]:" + str[1] +",str[2]:" + str[2]);

			if (category.equals(str[0])) {
				List<Item> itemList = itemService.findByParentCategory(category, pageNum);
				model.addAttribute("itemList", itemList);
			}else if (category.equals(str[1])) {
				String parentChildCategory = str[0] + "/" + str[1] + "/";
				List<Item> itemList = itemService.findByParentChildCategory(parentChildCategory, pageNum);
				model.addAttribute("itemList", itemList);
			}else if (category.equals(str[2])) {
				String paraChilGraChilCategory = str[0] + "/" + str[1] + "/" + str[2];
				List<Item> itemList = itemService.findByParentChildCategory(paraChilGraChilCategory, pageNum);
				model.addAttribute("itemList", itemList);
			}
		}

		//大カテゴリーのリストをスコープに格納
		List<Category> parentList = categoryService.selectParent();
		model.addAttribute("parentList", parentList);

		//中カテゴリーのリストをスコープに格納
		List<Category> childList = categoryService.selectChild();
		model.addAttribute("childList", childList);

		//小カテゴリーのリストをスコープに格納
		List<Category> grandChildList = categoryService.selectGrandChild();
		model.addAttribute("grandChildList", grandChildList);

		return "list";
	}
}
