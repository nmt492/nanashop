package vn.devpro.personalproject.controller.frontend;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import vn.devpro.personalproject.controller.BaseController;
import vn.devpro.personalproject.dto.Cart;
import vn.devpro.personalproject.dto.CartProduct;
import vn.devpro.personalproject.dto.Contact;
import vn.devpro.personalproject.dto.NanaShopConstants;
import vn.devpro.personalproject.model.Product;
import vn.devpro.personalproject.model.SaleOrder;
import vn.devpro.personalproject.model.SaleOrderProduct;
import vn.devpro.personalproject.model.User;
import vn.devpro.personalproject.service.ProductService;
import vn.devpro.personalproject.service.SaleOrderService;
import vn.devpro.personalproject.service.UserService;

@Controller
public class CartController extends BaseController implements NanaShopConstants {

	@Autowired
	private ProductService productService;
	
	@Autowired
	private SaleOrderService orderService;
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value = "/add-to-cart", method = RequestMethod.POST)
	public ResponseEntity<Map<String, Object>> addToCart(final Model model, final HttpServletRequest request, final HttpServletResponse response,
			@RequestBody CartProduct addedProduct) throws IOException {
		
		//lay session
		HttpSession session = request.getSession();
		Cart cart = null;
		
		// kiem tra xem co gio hang chua 
		if(session.getAttribute("cart")== null) {
			cart = new Cart();
		}else {
			//da co gio hang
			cart = (Cart) session.getAttribute("cart");
		}
		
		// them hang vao gio
		//lay product trong db
		
		Product dbProduct = (Product) productService.getById(addedProduct.getProductId());
		
		//tao moi mot cart voi 2 truong hop
		
		int index = cart.findProductById(dbProduct.getId());
		if(index != -1) {//th1: san pham da co trong gio -> tang quantity
			cart.getCartProducts().get(index).setQuantity(cart.getCartProducts().get(index).getQuantity()+
					addedProduct.getQuantity());
		}else {
			//th2: san pham chua co trong gio -> them moi
			
			addedProduct.setAvatar(dbProduct.getAvatar());
			addedProduct.setProductName(dbProduct.getName());
			addedProduct.setPrice(dbProduct.getPrice());
			
			cart.getCartProducts().add(addedProduct);
		}
		
		//thiet lap bien session cho cart
		session.setAttribute("cart", cart);
		
		// tra ve tong so san pham
		
		Map<String, Object> jsonResult = new HashMap<String, Object>();
		jsonResult.put("code", 404);
		jsonResult.put("message", "da them san pham '"+ dbProduct.getName() +"' vao gio hang");
		jsonResult.put("totalCartProducts", cart.totalCartProducts());
		return ResponseEntity.ok(jsonResult);
	}
	
	@RequestMapping(value = "/cart", method = RequestMethod.GET)
	public String cart(final Model model , final HttpServletRequest request, final HttpServletResponse response ) throws IOException{
		//lay gio hang
		HttpSession session = request.getSession();
		Cart cart = null;
		String message = null;
		String errorMessage = null;
		if(session.getAttribute("cart") != null) {
			cart = (Cart) session.getAttribute("cart");
			message = "Co "+ cart.totalCartProducts() + " trong gio hang";
			model.addAttribute("totalCartPrice",cart.totalCartPrice());
		}else {
			message = "Khong co san pham nao trong gio hang";
		}
		
		
		model.addAttribute("message", message);
		if(isLogined()) {
			model.addAttribute("user", getLoginedUser());
		}else {
			model.addAttribute("user", new User());
		}
		return "frontend/cart";
	}
	
	
	@RequestMapping(value = "/update-product-quantity", method = RequestMethod.POST)
	public ResponseEntity<Map<String, Object>> updateProductQuantity(
			final Model model,
			final HttpServletRequest request,
			final HttpServletResponse response,
			@RequestBody CartProduct updatedProduct) throws IOException{
		
		HttpSession session = request.getSession();
		//Product dbProduct = productService.getById(updatedProduct.getProductId());
		
		Cart cart = null;
		if(session.getAttribute("cart") != null) {
			cart = (Cart) session.getAttribute("cart");
			int index = cart.findProductById(updatedProduct.getProductId());
			cart.getCartProducts().get(index).setQuantity(cart.getCartProducts().get(index).getQuantity() + updatedProduct.getQuantity());
		}
		
		Map<String, Object> jsonResult = new HashMap<String, Object>();
		jsonResult.put("code", 404);
		
		jsonResult.put("totalCartProducts", cart.totalCartProducts());
		model.addAttribute("totalCartPrice",cart.totalCartPrice());
		return ResponseEntity.ok(jsonResult);
	}
	
    //------delete order---------------
	@RequestMapping(value = "/product-cart-delete/{productId}", method = RequestMethod.GET)
	public String deleteOrder(final Model model,final HttpServletRequest request, final HttpServletResponse response, @PathVariable("productId") int productId )//lay productId khi ngta click edit
			throws IOException {
		HttpSession session = request.getSession();
		Cart cart = null;
		
		if(session.getAttribute("cart") != null) {
			cart = (Cart) session.getAttribute("cart");
			int index = cart.findProductById(productId);
			cart.getCartProducts().remove(index);
		}
		return "redirect:/cart";
	}
	
	@RequestMapping(value = "/place-order", method = RequestMethod.POST)
	public ResponseEntity<Map<String, Object>> placeOrderProduct(
			final Model model,
			final HttpServletRequest request,
			final HttpServletResponse response,
			@RequestBody Contact contactOrder)throws IOException{
		
		Map<String, Object> jsonResult = new HashMap<String, Object>();
		
		// kiem tra thong tin bat buoc 
		if(contactOrder.getTxtName().isEmpty()) {
			jsonResult.put("code", 100);
			jsonResult.put("message", "Ban chua nhap ho ten");
		}
		else if(contactOrder.getTxtMobile().isEmpty()){
			jsonResult.put("code", 101);
			jsonResult.put("message", "Ban chua nhap so dien thoai");
		}
		else {
			//lay gio hang de luu danh sach vao tbl_sale_oder_product
			HttpSession session = request.getSession();
			if(session.getAttribute("cart") != null) {
				Cart cart = (Cart) session.getAttribute("cart");
				if(cart.totalCartProducts().intValue() > 0) { // co hang trong gio
					//tao don hang
					SaleOrder saleOrder = new SaleOrder();
					saleOrder.setCustomerName(contactOrder.getTxtName());
					saleOrder.setCustomerEmail(contactOrder.getTxtEmail());
					saleOrder.setCustomerAddress(contactOrder.getTxtAddress());
					saleOrder.setCustomerMobile(contactOrder.getTxtMobile());
					saleOrder.setTotal(cart.totalCartPrice());
					saleOrder.setCode(contactOrder.getTxtMobile());
					saleOrder.setCreateDate(new Date());
					saleOrder.setStatus(false);
					
					//set moi quan he voi user
					
					User user = new User();
					user.setId(1);
					saleOrder.setUser(user);
					
					// duyet danh sach san pham trong gio hang va luu vao tbl_sale_order_product
					for(CartProduct cartProduct : cart.getCartProducts()) {
						SaleOrderProduct saleOrderProduct = new SaleOrderProduct();
						saleOrderProduct.setQuantity(cartProduct.getQuantity().intValue());
						Product product = (Product) productService.getById(cartProduct.getProductId());
						saleOrderProduct.setProduct(product);
						saleOrder.addRelationalSaleOrderProduct(saleOrderProduct);// luu vao bang tbl_sale_order_product
					}
					// luu don hang vao tbl_sale_order
					orderService.saveOrUpdate(saleOrder);
					// xoa gio hang sau khi luu thanh cong
					cart = new Cart();
					session.setAttribute("cart", null);
					jsonResult.put("code", 200);
					jsonResult.put("message", "Ban da dat hang thanh cong");
					
					session.setAttribute("checkout", true);
				}
				else {
					jsonResult.put("code", 103);
					jsonResult.put("message", "Co loi internet");
				}
			}
			else {
				jsonResult.put("code", 104);
				jsonResult.put("message", "Co loi internet");
			}
		}
		return ResponseEntity.ok(jsonResult); 
	}
	
	
}
