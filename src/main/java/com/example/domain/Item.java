package com.example.domain;


/**
 * 商品ドメイン.
 * 
 * @author atsushi
 *
 */
public class Item {

	/**　商品ID　*/
	private Integer id;
	/**　商品名　*/
	private String name;
	/**　商品状態ID　*/
	private Integer itemConditionId;
	/**　商品カテゴリーID　*/
	private Integer categoryId;
	/**　ブランド名　*/
	private String brandName;
	/**　金額　*/
	private double price;
	/**　？？　*/
	private Integer shipping;
	/**　商品説明　*/
	private String itemDescription;
	/** カテゴリー情報 */
	private String nameAll;
	private Integer pageNum;
	

	
	
	@Override
	public String toString() {
		return "Item [id=" + id + ", name=" + name + ", itemConditionId=" + itemConditionId + ", categoryId="
				+ categoryId + ", brandName=" + brandName + ", price=" + price + ", shipping=" + shipping
				+ ", itemDescription=" + itemDescription + ", nameAll=" + nameAll + ", pageNum=" + pageNum + "]";
	}

	public Integer getPageNum() {
		return pageNum;
	}

	public void setPageNum(Integer pageNum) {
		this.pageNum = pageNum;
	}

	public String getNameAll() {
		return nameAll;
	}
	public void setNameAll(String nameAll) {
		this.nameAll = nameAll;
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
	public Integer getItemConditionId() {
		return itemConditionId;
	}
	public void setItemConditionId(Integer itemConditionId) {
		this.itemConditionId = itemConditionId;
	}
	public Integer getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}
	public String getBrandName() {
		return brandName;
	}
	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public Integer getShipping() {
		return shipping;
	}
	public void setShipping(Integer shipping) {
		this.shipping = shipping;
	}
	public String getItemDescription() {
		return itemDescription;
	}
	public void setItemDescription(String itemDescription) {
		this.itemDescription = itemDescription;
	}
}
