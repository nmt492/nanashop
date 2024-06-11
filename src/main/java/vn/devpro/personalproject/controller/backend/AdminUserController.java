package vn.devpro.personalproject.controller.backend;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import vn.devpro.personalproject.controller.BaseController;
import vn.devpro.personalproject.model.Role;
import vn.devpro.personalproject.model.User;
import vn.devpro.personalproject.service.RoleService;
import vn.devpro.personalproject.service.UserService;

@Controller
public class AdminUserController extends BaseController {
	//su dung service de thao tac du lieu voi database (tbl_user)
	@Autowired
	private UserService userService;
	
	@Autowired
	private RoleService roleService;
	
	@RequestMapping(value = "/admin/user-list", method = RequestMethod.GET)
	public String userList(final Model model) throws IOException {
		//lay danh sach user tu tbl_user trong db
		List<User> users = userService.findAllActive();
		model.addAttribute("users", users);
		return "backend/user-list";
	}
	
	@RequestMapping(value = "/admin/user-add", method = RequestMethod.GET)
	public String userAdd(final Model model) throws IOException {
		//lay danh sach user tu tbl_user trong db
		List<User> users = userService.findAll();
		model.addAttribute("users", users);
		
		List<Role> roles = roleService.findAll();
		model.addAttribute("roles", roles);
		
		User user = new User();
		user.setCreateDate(new Date());
		model.addAttribute("user", user);
		return "backend/user-add";
	}
	
	//Save user to db
	@RequestMapping(value = "/admin/user-add-save", method = RequestMethod.POST)
	public String userAddSave(final Model model, final HttpServletRequest request, @ModelAttribute("user") User user) throws IOException {
		
		if(!StringUtils.isEmpty(request.getParameter("role")) ) {
			int roleId = Integer.parseInt(request.getParameter("role"));
			
			//lay role tu tbl_role trong db
			
			Role role = roleService.getById(roleId);
			
			//luu UserId va RoleId 
			user.addRelationalUserRole(role);
			
			// ma hoa password bang bcrypt
//			user.setPassword(new BCryptPasswordEncoder(4).encode(user.getPassword()));
			//luu user vao bang tbl_user
			userService.saveOrUpdate(user);
		}
		//ko chon role thi k luu user
		return "backend/user-add";
	}
	
	
	//------edit user---------------
	@RequestMapping(value = "/admin/user-edit/{userId}", method = RequestMethod.GET)
	public String userEdit(final Model model, @PathVariable("userId") int userId )//lay userId khi ngta click edit
			throws IOException {
		//lay user tu tbl_user trong db
		
		User user = userService.getById(userId);
		model.addAttribute("user", user);

		List<User> users = userService.findAll();
		model.addAttribute("users", users);
		return "backend/user-edit";
	}
	
     //Save user to db
	@RequestMapping(value = "/admin/user-edit-save", method = RequestMethod.POST)
	public String userEditSave(final Model model, final HttpServletRequest request, @ModelAttribute("user") User user) throws IOException {
		
		
			
			//luu user vao bang tbl_user
			userService.saveOrUpdate(user);
		
		return "redirect:/admin/user-list";
	}
	
//	    //------delete user---------------
//		@RequestMapping(value = "/admin/user-delete/{userId}", method = RequestMethod.GET)
//		public String deleteUser(final Model model, @PathVariable("userId") int userId )//lay userId khi ngta click edit
//				throws IOException {
//			
//			userService.deleteUserById(userId);
//			return "redirect:/admin/user-list";
//		}
	
	//-----------Inactive user-------------	
		@RequestMapping(value = "/admin/user-delete/{userId}", method = RequestMethod.GET)
		public String userDelete(final Model model,
				@PathVariable("userId") int userId //Lay user id khi click Edit
				) throws IOException{
			//Lay user tu tbl_user trong DB
			User user = userService.getById(userId);
			user.setStatus(Boolean.FALSE);
			
			userService.saveOrUpdate(user);
			
			return "redirect:/admin/user-list";
		}
}
