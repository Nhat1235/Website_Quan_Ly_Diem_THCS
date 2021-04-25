package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.model.HocSinh;
import com.example.demo.model.TaiKhoanGv;
import com.example.demo.model.TaiKhoanHs;
import com.example.demo.repositories.HocSinhRepository;
import com.example.demo.repositories.TaiKhoanGvRepository;
import com.example.demo.repositories.TaiKhoanHSRepository;

@Controller
@RequestMapping(value = "BGH")
public class BGHController {

	@Autowired
	TaiKhoanGvRepository taiKhoanGvRepository;
	
	@Autowired
	TaiKhoanHSRepository taiKhoanHSRepository;
	
	@Autowired
	HocSinhRepository hocsinhrepo;
	
	@GetMapping
	public String showTaiKhoanGV(Model model , Authentication authentication) {
		
		TaiKhoanGv ten = taiKhoanGvRepository.findAllDetail(authentication.getName());
		
		List<TaiKhoanGv> tkgv= taiKhoanGvRepository.findAll();
		
		List<TaiKhoanHs> tkhs = taiKhoanHSRepository.findAll();
		
		model.addAttribute("tkgv",tkgv);
		
		model.addAttribute("tkhs",tkhs);
		
		model.addAttribute("ten",ten);
		
		return "BGH";
		
	}
	
}
