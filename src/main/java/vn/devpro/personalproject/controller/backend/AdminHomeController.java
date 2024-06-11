package vn.devpro.personalproject.controller.backend;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import vn.devpro.personalproject.controller.BaseController;
import vn.devpro.personalproject.dto.NanaShopConstants;

@Controller
public class AdminHomeController extends BaseController implements NanaShopConstants {
	// view trang home admin
	@RequestMapping(value = "/admin/home", method = RequestMethod.GET)
	public String homeAdmin() {
		return "backend/home";
	}
	
	@RequestMapping(value = "/admin/charts", method = RequestMethod.GET)
	public String charts() {
		return "backend/charts";
	}
}
