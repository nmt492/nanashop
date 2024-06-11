package vn.devpro.personalproject.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import vn.devpro.personalproject.dto.SearchModel;
import vn.devpro.personalproject.model.Product;
import vn.devpro.personalproject.model.SaleOrder;
import vn.devpro.personalproject.model.User;

@Service
public class SaleOrderService extends BaseService<SaleOrder>{

	@Override
	public Class<SaleOrder> clazz() {
		// TODO Auto-generated method stub
		return SaleOrder.class;
	}
	
	public List<SaleOrder> findAllActive(){
		return super.executeNativeSql("SELECT * FROM tbl_user WHERE status=1");
	}
	
	@Transactional
	public void deleteSaleOrderById (int id) {
		super.deleteById(id);
	}
	
	
}
