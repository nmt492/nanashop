package vn.devpro.personalproject.controller.backend;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import vn.devpro.personalproject.controller.BaseController;
import vn.devpro.personalproject.dto.NanaShopConstants;
import vn.devpro.personalproject.model.SaleOrder;
import vn.devpro.personalproject.service.SaleOrderService;

@Controller
public class AdminSaleOrderController extends BaseController implements NanaShopConstants {

	@Autowired
	private SaleOrderService saleOrderService;
	
	@RequestMapping(value = "/admin/order", method = RequestMethod.GET)
	public String orderList(final Model model, final HttpServletRequest request) throws IOException {
		//lay danh sach user tu tbl_user trong db
				List<SaleOrder> saleOrders = saleOrderService.findAll();
				model.addAttribute("saleOrders", saleOrders);
				return "backend/order";
				
	}
}
