package vn.devpro.personalproject.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tbl_product_image")
public class ProductImage extends BaseModel {
	
	@Column(name = "title", length = 500 , nullable = true)
	private String title;
	
	@Column(name = "path", length = 300 , nullable = true)
	private String path;
	
	//Mapping many-to-one : ProductImage to product----------------------------------
	
		@ManyToOne(fetch = FetchType.EAGER)
		@JoinColumn(name = "product_id")
		private Product product;

		public Product getProduct() {
			return product;
		}

		public void setProduct(Product product) {
			this.product = product;
		}
		
		//-----------Mapping many-to-one: productImage-to-user (user create productImage)------------------
		@ManyToOne(fetch = FetchType.EAGER)
		@JoinColumn(name = "create_by")
		private User userCreateProductImage;

	//-----------Mapping many-to-one: productImage-to-user (user update productImage)------------------
		@ManyToOne(fetch = FetchType.EAGER)
		@JoinColumn(name = "update_by")
		private User userUpdateProductImage;

		
		
		public ProductImage() {
		super();
	}

		public String getTitle() {
			return title;
		}

		public void setTitle(String title) {
			this.title = title;
		}

		public String getPath() {
			return path;
		}

		public void setPath(String path) {
			this.path = path;
		}

		public User getUserCreateProductImage() {
			return userCreateProductImage;
		}

		public void setUserCreateProductImage(User userCreateProductImage) {
			this.userCreateProductImage = userCreateProductImage;
		}

		public User getUserUpdateProductImage() {
			return userUpdateProductImage;
		}

		public void setUserUpdateProductImage(User userUpdateProductImage) {
			this.userUpdateProductImage = userUpdateProductImage;
		}
		
		

		
}
