package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.model.HocSinh;
import com.example.demo.repositories.HocSinhRepository;

@Controller
@RequestMapping(value = "hocsinh")
public class HocSinhController {

	@Autowired
	HocSinhRepository hocSinhRepository;

	@GetMapping(value = "show")
	public String show(Model model) {
		List<HocSinh> hocsinh = hocSinhRepository.findAll();
		
		model.addAttribute("hocsinh" , hocsinh);
		
		return "hocsinh/show";
	}
	
	@RequestMapping(value = "addHS")
	public String addHocSinh(Model model) {
		
		model.addAttribute("hocsinh" , new HocSinh());
		
		return "hocsinh/add";
	}
	@PostMapping(value = "saveHS")
	public String doSaveHS(@ModelAttribute("HocSinh") HocSinh hocSinh,Model model) {
	
		hocSinhRepository.save(hocSinh);
		
		return "redirect:show";
		
	}
	
}
