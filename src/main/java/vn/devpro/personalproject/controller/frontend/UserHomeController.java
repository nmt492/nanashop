package vn.devpro.personalproject.controller.frontend;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import vn.devpro.personalproject.controller.BaseController;
import vn.devpro.personalproject.dto.NanaShopConstants;
import vn.devpro.personalproject.dto.SearchModel;
import vn.devpro.personalproject.model.Category;
import vn.devpro.personalproject.model.Product;
import vn.devpro.personalproject.model.ProductImage;
import vn.devpro.personalproject.model.User;
import vn.devpro.personalproject.service.CategoryService;
import vn.devpro.personalproject.service.ProductImageService;
import vn.devpro.personalproject.service.ProductService;
import vn.devpro.personalproject.service.UserService;

@Controller
public class UserHomeController extends BaseController implements NanaShopConstants {
	
	@Autowired
	private ProductService productService;
	
	@Autowired 
	private ProductImageService productImageService;
	
	@Autowired 
	private CategoryService categoryService;
	@Autowired 
	private UserService userService;
	
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String index(final Model model , final HttpServletRequest request, final HttpServletResponse response ) throws IOException{
		
		List<Category> categories = categoryService.findAllActive();
		model.addAttribute("categories", categories);
		
		if(isLogined()) {
			model.addAttribute("user", getLoginedUser());
		}else {
			model.addAttribute("user", new User());
		}
		
		List<Product> products = productService.findAllActive();
		model.addAttribute("products",products);
		model.addAttribute("totalProducts",products.size());
				
		return "frontend/index";
	}
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(final Model model , final HttpServletRequest request, final HttpServletResponse response ) throws IOException{
		
		List<Category> categories = categoryService.findAllActive();
		model.addAttribute("categories", categories);
		
		if(isLogined()) {
			model.addAttribute("user", getLoginedUser());
		}else {
			model.addAttribute("user", new User());
		}
		
		List<Product> products = productService.findAllActive();
		model.addAttribute("products",products);
		model.addAttribute("totalProducts",products.size());
				
		return "frontend/list";
	}
	
	@RequestMapping(value = "/detail/{productId}", method = RequestMethod.GET)
	public String productDetail(final Model model, final HttpServletRequest request,
			final HttpServletResponse reponse, @PathVariable("productId") int productId
			) throws IOException{
		
//		@PathVariable
//		getById

		Product product = (Product) productService.getById(productId);
		ProductImage productImage = productImageService.getById(productId);
		
		model.addAttribute("product", product);
		model.addAttribute("productImage", productImage);
		return "frontend/detail";
	}
	@RequestMapping(value = "/shop/{categoryId}", method = RequestMethod.GET)
	public String shop(final Model model , final HttpServletRequest request,
			final HttpServletResponse response, @PathVariable("categoryId") int categoryId) throws IOException{
		
		Category category = categoryService.getById(categoryId);
		model.addAttribute("category", category);
		List<Product> products = productService.findByCategory(category);
		model.addAttribute("totalProducts",products.size());
		return "frontend/shop";
		
	}
	
	@RequestMapping(value = "/search", method = RequestMethod.GET)
	public String search(final Model model , final HttpServletRequest request,
			final HttpServletResponse response, @RequestParam String q) throws IOException{

		List<Category> categories = categoryService.findAll();
		model.addAttribute("categories", categories);
//		System.out.println("Search query: " + q);
		SearchModel product = new SearchModel();

		// Tìm kiếm với tiêu chí keyword
		product.setKeyword(q);
		
		// Tìm kiếm với tiêu chí category
				product.setCategoryId(0);// Không chọn category
				if (!StringUtils.isEmpty(request.getParameter("categoryId"))) {
					product.setCategoryId(Integer.parseInt(request.getParameter("categoryId")));
				}
		List<Product> products = productService.search(product);
		model.addAttribute("totalProducts", products.size());
		model.addAttribute("q", q);
		model.addAttribute("products", products);
		
		return "frontend/search";
		
	}

}
