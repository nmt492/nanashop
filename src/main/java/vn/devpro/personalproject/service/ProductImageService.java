package vn.devpro.personalproject.service;

import java.util.List;

import org.springframework.stereotype.Service;

import vn.devpro.personalproject.dto.NanaShopConstants;
import vn.devpro.personalproject.model.ProductImage;

@Service
public class ProductImageService extends BaseService<ProductImage> implements NanaShopConstants{

	@Override
	public Class<ProductImage> clazz() {
		return ProductImage.class;
	}
	
	public List<ProductImage> findAllImage(int productId){
		return super.executeNativeSql("select * from tbl_product_image where product_id = "+productId);
	}
	
	public List<ProductImage> findAllActive() {
		return super.executeNativeSql("SELECT * FROM tbl_product WHERE status=1");
	}
}
