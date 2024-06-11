package vn.devpro.personalproject.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import vn.devpro.personalproject.model.Category;
import vn.devpro.personalproject.model.Product;

@Service
public class CategoryService extends BaseService<Category> {

	@Override
	public Class<Category> clazz() {
		
		return Category.class;
	}
	
	public List<Category> findAllActive(){
		return super.executeNativeSql("SELECT * FROM tbl_category WHERE status=1");
	}
	
	@Transactional
	public void deleteCategoryById(int id) {
		 super.deleteById(id);
	}
}
