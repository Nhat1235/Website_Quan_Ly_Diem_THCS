package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.model.HocKy;
import com.example.demo.model.HocSinh;
import com.example.demo.service.HocKyService;

@Controller
@RequestMapping(value = "/hk")

public class HocKyController {

	@Autowired
	HocKyService hocKyService;

	@GetMapping(value = "showhocky")
	public String showHK(Model model) {

		List<HocKy> hocky = hocKyService.getAllHocKy();

		model.addAttribute("hocky", hocky);

		return "hocky/show";

	}

	
	@RequestMapping(value = "addHK")
	public String addHK(Model model) {
		
		model.addAttribute("hocky" , new HocKy());
		
		return "hocky/add";
	}
	@PostMapping(value = "saveHK")
	public String doSaveHS(@ModelAttribute("HocKy") HocKy hocky,Model model) {
	
		hocKyService.save(hocky);
		
		return "redirect:showhocky";
		
	}
	
}
