package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.model.HocSinh;
import com.example.demo.model.KhoaHoc;
import com.example.demo.model.TaiKhoanGv;
import com.example.demo.repositories.TaiKhoanGvRepository;
import com.example.demo.service.KhoaHocService;

@Controller
@RequestMapping(value = "khoahoc")
public class KhoaHocController {
	
	@Autowired
	TaiKhoanGvRepository TKGvRepo;
	
	@Autowired
	KhoaHocService khoaHocService;
	
	@GetMapping(value = "showkhoahoc")
	public String showKhoaHoc(Model model, Authentication authentication) {
		
		TaiKhoanGv tkgv = TKGvRepo.findAllDetail(authentication.getName());
		
		List<KhoaHoc> khoaHoc = khoaHocService.getAllKhoaHoc();
		
		model.addAttribute("tkgv", tkgv);
		
		model.addAttribute("khoahoc" , khoaHoc);
		
		model.addAttribute("khoahoc1" , new KhoaHoc());
		return "khoahoc/show";
	}
	@RequestMapping(value = "addKhoaH")
	public String addKhoaHoc(Model model) {
		
		model.addAttribute("khoahoc" , new KhoaHoc());
		
		return "khoahoc/add";
	}
	@PostMapping(value = "saveKhoaH")
	public String doSaveKhoaH(@ModelAttribute("KhoaHoc") KhoaHoc khoahoc,Model model) {
	
		khoaHocService.save(khoahoc);
		
		return "redirect:showkhoahoc";
		
	}
	
	
}
