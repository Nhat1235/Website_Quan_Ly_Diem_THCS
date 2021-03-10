package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.model.GiaoVien;
import com.example.demo.model.HocKy;
import com.example.demo.service.GiaoVienService;

@Controller
@RequestMapping(value = "/gv")
public class GiaoVienController {

	@Autowired
	GiaoVienService giaoVienService;
	
	
	@GetMapping(value = "showGV")
	public String showGV(Model model) {

		List<GiaoVien> giaovien = giaoVienService.getAllGV();

		model.addAttribute("giaovien", giaovien);

		return "gv/show";

	}

	
	@RequestMapping(value = "addGV")
	public String addGV(Model model) {
		
		model.addAttribute("giaovien" , new GiaoVien());
		
		return "gv/add";
	}
	@PostMapping(value = "saveGV")
	public String doSaveGV(@ModelAttribute("GiaoVien") GiaoVien giaovien,Model model) {
	
		giaoVienService.save(giaovien);
		
		return "redirect:showGV";
		
	}
	
}
