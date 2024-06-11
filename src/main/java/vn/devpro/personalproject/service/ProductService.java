package vn.devpro.personalproject.service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import vn.devpro.personalproject.dto.NanaShopConstants;
import vn.devpro.personalproject.dto.SearchModel;
import vn.devpro.personalproject.model.Category;
import vn.devpro.personalproject.model.Product;
import vn.devpro.personalproject.model.ProductImage;
import vn.devpro.personalproject.model.User;

@Service
public class ProductService<E> extends BaseService<Product> implements NanaShopConstants{

	@Override
	public Class<Product> clazz() {
		
		return Product.class;
	}
	
	public List<Product> findAllActive(){
		return super.executeNativeSql("SELECT * FROM tbl_product WHERE status=1");
	}
	
	@Transactional
	public void deleteProductById(int id) {
		 super.deleteById(id);
	}
	
	//ham kiem tra 1 file co dc upload
	public boolean isUploadFile(MultipartFile file) {
		if(file == null || file.getOriginalFilename().isEmpty()) {
			return false;
		}
		return true;
	}
	
	//ham kiem tra ds file co dc uploadfile ko
	
	public boolean isUploadFiles(MultipartFile[] file) {
		if(file == null || file.length==0) {
			return false; // ko up load
		}
		return true; // co upload it nhat 1 file
	}
	
	@Transactional
	public Product saveAddProduct(Product product, MultipartFile avatarFile, MultipartFile[] imageFiles) throws IOException {
		
		//luu avatar File moi neu nguoi dung co upload avatar
		if(isUploadFile(avatarFile)) { //co file upload
			//luu file vao thu muc product/Avatar
			String path = FOLDER_UPLOAD + "Product/Avatar/"+avatarFile.getOriginalFilename();
			File file = new File(path);
			avatarFile.transferTo(file);
			product.setAvatar("Product/Avatar/"+avatarFile.getOriginalFilename());
		}
		
		//luu image file
		if(isUploadFiles(imageFiles)) {
			//duyet ds file images
			for(MultipartFile imageFile : imageFiles) {
				if(isUploadFile(imageFile)) {//file co upload
					//Luu file vao thu muc product Image
					
					String path = FOLDER_UPLOAD + "Product/Image/"+imageFile.getOriginalFilename();
					File file = new File(path);
					imageFile.transferTo(file);
					//luu duong dan vao tbl_product_image
					
					ProductImage productImage = new ProductImage();
					productImage.setTitle(imageFile.getOriginalFilename());
					productImage.setPath("Product/Image/"+imageFile.getOriginalFilename());
					productImage.setStatus(Boolean.TRUE);
					productImage.setCreateDate(new Date());
					product.addRelationalProductImage(productImage);//luu sang bang tbl_product_image
				}
			}
		}
		return super.saveOrUpdate(product);
	}
	
	//-------------------Save edit product--------------------
	
	@Transactional
	public Product saveEditProduct(Product product, MultipartFile avatarFile, MultipartFile[] imageFiles) throws IOException {
		//lay product trong db
			Product dbProduct = super.getById(product.getId());
		//luu avatar File
		if(isUploadFile(avatarFile)) { //co file upload
			//xoa avatar cu
			String path = FOLDER_UPLOAD + dbProduct.getAvatar();
			File file = new File(path);
			file.delete();
			
			//luu avt moi  vao thu muc product/Avatar
		    path = FOLDER_UPLOAD + "Product/Avatar/"+avatarFile.getOriginalFilename();
		    file = new File(path);
			avatarFile.transferTo(file);
			product.setAvatar("Product/Avatar/"+avatarFile.getOriginalFilename());
		}else {
			//nguoi dung k upload avt file
			//giu nguyen avt cu 
			product.setAvatar(dbProduct.getAvatar());
		}
		
		//luu image file
		if(isUploadFiles(imageFiles)) {
			//duyet ds file images
			for(MultipartFile imageFile : imageFiles) {
				if(isUploadFile(imageFile)) {//file co upload
					//Luu file vao thu muc product Image
					
					String path = FOLDER_UPLOAD + "Product/Image/"+imageFile.getOriginalFilename();
					File file = new File(path);
					imageFile.transferTo(file);
					//luu duong dan vao tbl_product_image
					
					ProductImage productImage = new ProductImage();
					productImage.setTitle(imageFile.getOriginalFilename());
					productImage.setPath("Product/Image/"+imageFile.getOriginalFilename());
					productImage.setStatus(Boolean.TRUE);
					productImage.setCreateDate(new Date());
					product.addRelationalProductImage(productImage);//luu sang bang tbl_product_image
				}
			}
		}
		return super.saveOrUpdate(product);
	}
	
	@Autowired
	private ProductImageService productImageService;
		//-----------------delete product-------------
	
	@Transactional
	public void deleteProductById1(int productId) {
		//lay product trong db
		Product product =super.getById(productId);
		//lay ds anh cua product trong tbl_product_image
		String sql = "select * from tbl_product_image where product_id=" + productId;
		List<ProductImage> productImages = productImageService.executeNativeSql(sql);
		
		//xoa lan luot cac anh cua product trong product Image va xoa lan luot cac duong dan anh trong tbl_product_image
		for(ProductImage productImage : productImages) {
			//xoa file
			String path = FOLDER_UPLOAD + productImage.getPath();
			File file = new File(path);
			file.delete();
			
			//xoa thong tin anh trong tbl_product_image
			//product.removeRelationalProductImage(productImage);//restrict
		}
		//xoa file avt trong thu muc product/Avatar
		String path = FOLDER_UPLOAD + product.getAvatar();
		File file = new File(path);
		file.delete();
		
		//xoa product db
		super.delete(product);
	}
	
	//search product
	
	public List<Product> searchProduct(SearchModel productSearch) {
		// tao cau lenh truy van sql
		String sql = "select * from tbl_product p where 1=1";
		
		//tim kiem voi status
		if(productSearch.getStatus() != 2) { // co chon active or inactive
			sql += " and p.status="+ productSearch.getStatus();
		}
		
		//tim kiem voi tieu chi category
		if(productSearch.getCategoryId() !=0) {
			sql += " and p.category_id=" + productSearch.getCategoryId();
		}
		// tim kiem voi tieu chi keyword
		if(!StringUtils.isEmpty(productSearch.getKeyword())) {
			String keyword = productSearch.getKeyword().toLowerCase();
			sql += " and (LOWER(p.name) like '%" +keyword + "%'" + "or LOWER(p.short_description) like '%"+ keyword +"%'" + "or LOWER(p.seo) like '%"+ keyword +"%') " ;
		}
		
		if(!StringUtils.isEmpty(productSearch.getBeginDate()) && !StringUtils.isEmpty(productSearch.getEndDate())) {
			String beginDate = productSearch.getBeginDate();
			String endDate = productSearch.getEndDate();
			sql += " and p.create_date between '" + beginDate + "' and '" + endDate + "'";
		}
		return super.executeNativeSql(sql,productSearch.getCurrentPage(),productSearch.getSizeOfPage());
	}
	
	public List<Product> search(SearchModel productSearch) {
		// tao cau lenh truy van sql
		String sql = "select * from tbl_product p where 1=1";
		
		//tim kiem voi tieu chi category
		if(productSearch.getCategoryId() !=0) {
			sql += " and p.category_id=" + productSearch.getCategoryId();
		}
		// tim kiem voi tieu chi keyword
		if(!StringUtils.isEmpty(productSearch.getKeyword())) {
			String keyword = productSearch.getKeyword().toLowerCase();
			sql += " and (LOWER(p.name) like '%" +keyword + "%'" + "or LOWER(p.short_description) like '%"+ keyword +"%'"
			+ "or LOWER(p.seo) like '%"+ keyword +"%') " ;
		}
		
		return super.executeNativeSql(sql);
	}
	
	public List<Product> getProductByCategory(Category category) {
		String sql = "select * from tbl_product p where 1=1";				
				
					sql += " and p.category_id=" + category.getId();
				return super.executeNativeSql(sql);
	}
	
}
