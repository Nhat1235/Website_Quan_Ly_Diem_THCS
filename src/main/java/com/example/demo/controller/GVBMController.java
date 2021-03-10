package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.example.demo.model.TaiKhoanGv;
import com.example.demo.repositories.TaiKhoanGvRepository;

@Controller
public class GVBMController {

	@Autowired
	private TaiKhoanGvRepository repo;
	
	@GetMapping("/")
	public String gvbmProfile(ModelMap model, Authentication authentication) {

		TaiKhoanGv tk = repo.findAllDetail(authentication.getName());
		System.out.println(tk);
		model.addAttribute("tk",tk);
		return "Profile";
	}
	
//	@GetMapping("/quan-ly-diem")
//	public String gvbm(ModelMap model) {
//       
//		
//		return "GVBM";
//	}
}
