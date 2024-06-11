package vn.devpro.personalproject.controller.backend;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import vn.devpro.personalproject.controller.BaseController;
import vn.devpro.personalproject.dto.NanaShopConstants;
import vn.devpro.personalproject.dto.NanaShopConstants;
import vn.devpro.personalproject.dto.SearchModel;
import vn.devpro.personalproject.model.Category;
import vn.devpro.personalproject.model.Product;
import vn.devpro.personalproject.model.Role;
import vn.devpro.personalproject.model.User;
import vn.devpro.personalproject.service.CategoryService;
import vn.devpro.personalproject.service.ProductService;
import vn.devpro.personalproject.service.UserService;

@Controller
public class AdminProductController extends BaseController implements NanaShopConstants {

	@Autowired
	private ProductService productService;

	@Autowired
	private UserService userService;

	@Autowired
	private CategoryService categoryService;

	@RequestMapping(value = "/admin/product-list", method = RequestMethod.GET)
	public String productList(final Model model, final HttpServletRequest request) throws IOException {
		// lay danh sach product tu tbl_product trong db
//		List<Product> products = productService.findAllActive();
		List<Category> categories = categoryService.findAll();
		
		model.addAttribute("categories", categories);
		
		SearchModel productSearch = new SearchModel();
		
		//tim voi tieu chi status
		productSearch.setStatus(2);
		
		if(!StringUtils.isEmpty(request.getParameter("status"))) {
			productSearch.setStatus(Integer.parseInt(request.getParameter("status")));
		}
		
		//tim voi tieu chi category
		
		productSearch.setCategoryId(0);
		if(!StringUtils.isEmpty(request.getParameter("categoryId"))) {
			productSearch.setCategoryId(Integer.parseInt(request.getParameter("categoryId")));
		}
		
		//tim kiem voi tieu chi keyword
		productSearch.setKeyword(request.getParameter("keyword"));
		
		//tim kiem voi tieu chi date
		productSearch.setBeginDate(request.getParameter("beginDate"));
		productSearch.setEndDate(request.getParameter("endDate"));
		
		
		//bat dau phan trang
		if(!StringUtils.isEmpty(request.getParameter("page"))) {//bam nut chuyen trang
			productSearch.setCurrentPage(Integer.parseInt(request.getParameter("page")));
		}else {
			productSearch.setCurrentPage(1);// lan dau truy cap hien thi luon trang 1
		}
		
		List<Product> allProducts = productService.searchProduct(productSearch);
		List<Product> products = new ArrayList<Product>();
		
		
		int totalPages = allProducts.size() / SIZE_OF_PAGE;
		if(allProducts.size() % SIZE_OF_PAGE > 0) {
			totalPages++;
		}
//		
		if(totalPages < productSearch.getCurrentPage()) {
			productSearch.setCurrentPage(1);
		}
		
		
		int firstIndex = (productSearch.getCurrentPage() -  1) * SIZE_OF_PAGE;
		int index = firstIndex, count = 0;
		while(index < allProducts.size() && count < SIZE_OF_PAGE) {
			products.add(allProducts.get(index));
			index++;
			count++;
		}
		
		//phân trang
				
		productSearch.setSizeOfPage(SIZE_OF_PAGE);//So ban ghi tren 1 trang
		productSearch.setTotalItems(allProducts.size());//Tong so san pham
		
		
		model.addAttribute("products", products);
		model.addAttribute("productSearch", productSearch);

		return "backend/product-list";
	}

	@RequestMapping(value = "/admin/product-add", method = RequestMethod.GET)
	public String productAdd(final Model model) throws IOException {
		// lay danh sach user tu tbl_user trong db
		List<User> users = userService.findAll();
		model.addAttribute("users", users);

		List<Category> categories = categoryService.findAll();
		model.addAttribute("categories", categories);
		Product product = new Product();
		product.setCreateDate(new Date());
		model.addAttribute("product", product);
		return "backend/product-add";
	}

//	// Save product to db
//	@RequestMapping(value = "/admin/product-add-save", method = RequestMethod.POST)
//	public String productAddSave(final Model model, final HttpServletRequest request,
//			@ModelAttribute("product") Product product) throws IOException {
//
//		// luu user vao bang tbl_user
//		productService.saveOrUpdate(product);
//		return "backend/product-add";
//	}

	// ------edit product---------------
	@RequestMapping(value = "/admin/product-edit/{productId}", method = RequestMethod.GET)
	public String productEdit(final Model model, @PathVariable("productId") int productId)// lay productId khi ngta click
																						// edit
			throws IOException {
		// lay product tu tbl_product trong db

		Product product = (Product) productService.getById(productId);
		model.addAttribute("product", product);

		List<Category> categories = categoryService.findAll();
		model.addAttribute("categories", categories);
		List<User> users = userService.findAll();
		model.addAttribute("users", users);
		return "backend/product-edit";
	}

	// Save product to db
	@RequestMapping(value = "/admin/product-edit-save", method = RequestMethod.POST)
	public String productEditSave(final Model model,
			@ModelAttribute("product") Product product,
			@RequestParam("avatarFile") MultipartFile avatarFile,
			@RequestParam("imageFiles") MultipartFile[] imageFiles)
			throws IOException {

		// luu product vao bang tbl_product
		productService.saveEditProduct(product, avatarFile, imageFiles);

		return "redirect:/admin/product-list";
	}
	
    // Save product to db
	@RequestMapping(value = "/admin/product-add-save", method = RequestMethod.POST)
	public String productAddSave(final Model model,
			@ModelAttribute("product") Product product,
			@RequestParam("avatarFile") MultipartFile avatarFile,
			@RequestParam("imageFiles") MultipartFile[] imageFiles
			) throws IOException {
		// luu user vao bang tbl_user
		productService.saveAddProduct(product,  avatarFile, imageFiles );
		return "redirect:/admin/product-list";
	}
	
	//-----------Inactive product-------------	
			@RequestMapping(value = "/admin/product-delete/{productId}", method = RequestMethod.GET)
			public String userDelete(final Model model,
					@PathVariable("productId") int productId //Lay user id khi click Edit
					) throws IOException{
				//Lay product tu tbl_product trong DB
				Product product = (Product) productService.getById(productId);
				product.setStatus(Boolean.FALSE);
				
				productService.saveOrUpdate(product);
				
				return "redirect:/admin/product-list";
		}
			
	
	// ------delete product---------------
//	@RequestMapping(value = "/admin/product-delete/{productId}", method = RequestMethod.GET)
//	public String deleteProduct(final Model model, @PathVariable("productId") int productId )//lay productId khi ngta click edit
//			throws IOException {
//		
//		
//		 productService.deleteProductById1(productId); 
//		return "redirect:/admin/product-list";
//	}

			
		
}
