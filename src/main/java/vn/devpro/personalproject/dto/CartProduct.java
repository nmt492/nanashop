package vn.devpro.personalproject.dto;

import java.math.BigDecimal;

public class CartProduct {
	
	private int productId;
	private String productName;
	private Integer quantity;
	private String avatar;
	private BigDecimal price;
	
	public CartProduct() {
		super();
	}
	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	public String getAvatar() {
		return avatar;
	}
	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	
	public BigDecimal totalPrice() {
		return this.price.multiply(new BigDecimal( this.quantity.toString()));
	}
}
