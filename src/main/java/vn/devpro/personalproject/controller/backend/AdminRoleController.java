package vn.devpro.personalproject.controller.backend;

import java.io.IOException;
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

import vn.devpro.personalproject.controller.BaseController;
import vn.devpro.personalproject.model.Role;
import vn.devpro.personalproject.model.User;
import vn.devpro.personalproject.service.RoleService;
import vn.devpro.personalproject.service.UserService;

@Controller
public class AdminRoleController extends BaseController {
	
	@Autowired
	private RoleService roleService;
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value = "/admin/role-list", method = RequestMethod.GET)
	public String roleList(final Model model) throws IOException {
		//lay danh sach role tu tbl_role trong db
		List<Role> roles = roleService.findAllActive();
		model.addAttribute("roles", roles);
		return "backend/role-list";
	}
	
	@RequestMapping(value = "/admin/role-add", method = RequestMethod.GET)
	public String roleAdd(final Model model) throws IOException {
		//lay danh sach user tu tbl_user trong db
		List<User> users = userService.findAll();
		model.addAttribute("users", users);
		
		
		
		Role role = new Role();
		role.setCreateDate(new Date());
		model.addAttribute("role", role);
		return "backend/role-add";
	}
	
	//Save role to db
		@RequestMapping(value = "/admin/role-add-save", method = RequestMethod.POST)
		public String roleAddSave(final Model model, final HttpServletRequest request, @ModelAttribute("Role") Role role) throws IOException {
			
			if(!StringUtils.isEmpty(request.getParameter("user")) ) {
				int userId = Integer.parseInt(request.getParameter("user"));
				
				//lay role tu tbl_role trong db
				
				User user = userService.getById(userId);
				
				//luu UserId va RoleId 
				user.addRelationalUserRole(role);
				
				//luu role vao bang tbl_role
				roleService.saveOrUpdate(role);
			}
			//ko chon role thi k luu user
			return "redirect:/admin/role-list";
		}
		
		//------edit role---------------
		@RequestMapping(value = "/admin/role-edit/{roleId}", method = RequestMethod.GET)
		public String roleEdit(final Model model, @PathVariable("roleId") int roleId )//lay roleId khi ngta click edit
				throws IOException {
			//lay role tu tbl_role trong db
			
			Role role = roleService.getById(roleId);
			model.addAttribute("role", role);

			List<User> users = userService.findAll();
			model.addAttribute("users", users);
			return "backend/role-edit";
		}
		
		 //Save role to db
		@RequestMapping(value = "/admin/role-edit-save", method = RequestMethod.POST)
		public String roleEditSave(final Model model, final HttpServletRequest request, @ModelAttribute("role") Role role) throws IOException {
			
			
				
				//luu user vao bang tbl_user
				roleService.saveOrUpdate(role);
			
			return "redirect:/admin/role-list";
		}
		
		//-----------Inactive role-------------	
				@RequestMapping(value = "/admin/role-delete/{roleId}", method = RequestMethod.GET)
				public String userDelete(final Model model,
						@PathVariable("roleId") int roleId //Lay user id khi click Edit
						) throws IOException{
					//Lay user tu tbl_user trong DB
					Role role = roleService.getById(roleId);
					role.setStatus(Boolean.FALSE);
					
					roleService.saveOrUpdate(role);
					
					return "redirect:/admin/role-list";
				}
}
