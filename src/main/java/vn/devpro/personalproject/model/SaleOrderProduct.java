package vn.devpro.personalproject.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tbl_sale_order_product")
public class SaleOrderProduct extends BaseModel{
	
	@Column(name = "quantity", nullable = true)
	private Integer quantity;
	
	@Column(name = "description", length = 500, nullable = true)
	private String description;
	
	//Mapping many-to-one : sale_order_product to product----------------------------------
	
			@ManyToOne(fetch = FetchType.EAGER)
			@JoinColumn(name = "product_id")
			private Product product;

			public Product getProduct() {
				return product;
			}

			public void setProduct(Product product) {
				this.product = product;
			}
			
	//Mapping many-to-one : sale_order_product to saleOrder----------------------------------
			
			@ManyToOne(fetch = FetchType.EAGER)
			@JoinColumn(name = "sale_order_id")
			private SaleOrder saleOrder;

			public SaleOrder getSaleOrder() {
				return saleOrder;
			}

			public void setSaleOrder(SaleOrder saleOrder) {
				this.saleOrder = saleOrder;
			}	
		
			//-----------Mapping many-to-one: SaleOrder-to-user (user create category)------------------
			@ManyToOne(fetch = FetchType.EAGER)
			@JoinColumn(name = "create_by")
			private User userCreateSaleOrderProduct;

			//-----------Mapping many-to-one: saleOrder-to-user (user update category)------------------
			@ManyToOne(fetch = FetchType.EAGER)
			@JoinColumn(name = "update_by")
			private User userUpdateSaleOrderProduct;

			public Integer getQuantity() {
				return quantity;
			}

			public void setQuantity(Integer quantity) {
				this.quantity = quantity;
			}

			public String getDescription() {
				return description;
			}

			public void setDescription(String description) {
				this.description = description;
			}

			public User getUserCreateSaleOrderProduct() {
				return userCreateSaleOrderProduct;
			}

			public void setUserCreateSaleOrderProduct(User userCreateSaleOrderProduct) {
				this.userCreateSaleOrderProduct = userCreateSaleOrderProduct;
			}

			public User getUserUpdateSaleOrderProduct() {
				return userUpdateSaleOrderProduct;
			}

			public void setUserUpdateSaleOrderProduct(User userUpdateSaleOrderProduct) {
				this.userUpdateSaleOrderProduct = userUpdateSaleOrderProduct;
			}
			
			
			
}
