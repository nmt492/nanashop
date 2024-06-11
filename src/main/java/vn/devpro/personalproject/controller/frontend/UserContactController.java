package vn.devpro.personalproject.controller.frontend;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import vn.devpro.personalproject.controller.BaseController;
import vn.devpro.personalproject.dto.Contact;
import vn.devpro.personalproject.dto.NanaShopConstants;

@Controller
public class UserContactController extends BaseController implements NanaShopConstants {
	
	@RequestMapping(value = "/contact", method = RequestMethod.GET)
	public String contact(final Model model, final HttpServletRequest request, final HttpServletResponse response) throws IOException {
		return "frontend/contact/contact";
	}
	
	@RequestMapping(value = "/contact-send", method = RequestMethod.POST)
	public String contactSend(final Model model, final HttpServletRequest request, final HttpServletResponse response) throws IOException {
		
		Contact contact = new Contact();
		contact.setTxtName(request.getParameter("txtName"));
		
		System.out.println("Name = "+contact.getTxtName());
		
		
		return "frontend/contact/contact";
	}
	
	@RequestMapping(value = "/contact-edit", method = RequestMethod.GET)
	public String contactEdit(final Model model, final HttpServletRequest request, final HttpServletResponse response) throws IOException {
		
		Contact contact = new Contact("Tuan","tuantientung@gmail.com","111001","HaNoi","hihi");
		//co the lay du lieu tu db
		model.addAttribute("contact", contact);// du lieu day sang form
		return "frontend/contact/contact-edit";
	}
	
	@RequestMapping(value = "/contact-edit-save", method = RequestMethod.POST)
	public ResponseEntity<Map<String,Object>>  contactEditNotify(final Model model, final HttpServletRequest request, 
			final HttpServletResponse response,final @RequestBody Contact contact) //lay du lieu tu ham ajax
			throws IOException {
		Map<String, Object> jsonResult = new HashMap<String, Object>();
		jsonResult.put("code", 200);
		jsonResult.put("message", "Thong tin quy khach '" + contact.getTxtName() + "' da duoc luu");
		return ResponseEntity.ok(jsonResult);
	}
	
	
	@RequestMapping(value = "/contact-sf", method = RequestMethod.GET)
	public String contactSF(final Model model, final HttpServletRequest request, final HttpServletResponse response) throws IOException {
		
		Contact contact = new Contact();
		//co the lay du lieu tu db
		model.addAttribute("contact", contact);// du lieu day sang form(View)
		return "frontend/contact/contact-sf";
	}
	
	@RequestMapping(value = "/contact-list", method = RequestMethod.POST)
	public String contactSFSend(final Model model, final HttpServletRequest request, 
			final HttpServletResponse response, @ModelAttribute("contact") Contact contact, //lay du lieu tu SF
			@RequestParam("contactFile") MultipartFile contactFile)			//lay file upload tu form							
					throws IOException {
		//co the lay du lieu tu db
		//model.addAttribute("contact", contact);// du lieu day sang form(View)
		
		//Luu file upload vao thu muc
		if(contactFile != null & !contactFile.getOriginalFilename().isEmpty()) { //co upload file 
			String path = FOLDER_UPLOAD + "ContactFiles/"+ contactFile.getOriginalFilename();
			File fileUpload = new File(path);
			contactFile.transferTo(fileUpload); // luu file vao thu muc ContactFiles
			
			//TODO : LUU DUONG DAN CUA FILE VAO DATABASE
		}
		
		model.addAttribute("contact", contact);
		model.addAttribute("filename", contactFile.getOriginalFilename());
		return "frontend/contact/contact-list";
	}
	
	@RequestMapping(value = "/contact-sf-edit", method = RequestMethod.GET)
	public String contactSFEdit(final Model model, final HttpServletRequest request, final HttpServletResponse response) throws IOException {
		
		Contact contact = new Contact("TINH","tinhte227@gmail.com","0000111","HN","hihi");
		//co the lay du lieu tu db
		model.addAttribute("contact", contact);// du lieu day sang form
		return "frontend/contact/contact-sf-edit";
	}
	
//	@RequestMapping(value = "/contact-list", method = RequestMethod.GET)
//	public String contactList(final Model model, final HttpServletRequest request, final HttpServletResponse response) throws IOException {
//		return "frontend/contact/contact-list";
//	}
	
	@RequestMapping(value = "/contact-sf-edit-save", method = RequestMethod.POST)
	public ResponseEntity<Map<String,Object>>  contactSFEditNotify(final Model model, final HttpServletRequest request, 
			final HttpServletResponse response,final @RequestBody Contact contact) //lay du lieu tu ham ajax
			throws IOException {
		Map<String, Object> jsonResult = new HashMap<String, Object>();
		jsonResult.put("code", 200);
		jsonResult.put("message", "Thong tin quy khach '" + contact.getTxtName() + "' da duoc luu");
		return ResponseEntity.ok(jsonResult);
	}
}
