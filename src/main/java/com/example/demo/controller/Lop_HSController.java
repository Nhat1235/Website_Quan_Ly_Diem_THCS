package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.model.HocSinh;
import com.example.demo.model.Lop;
import com.example.demo.model.Lop_hs;
import com.example.demo.repositories.HocSinhRepository;
import com.example.demo.service.LopService;
import com.example.demo.service.Lop_HSService;

@Controller
@RequestMapping(value = "lophs")
public class Lop_HSController {

	@Autowired
	Lop_HSService lop_HSService;
	
	@Autowired
	LopService lopService;
	
	@Autowired
	HocSinhRepository hocSinhrepo;
	
	
	@GetMapping(value = "show")
	public String show(Model model) {
		
		List<Lop_hs> list = lop_HSService.getLopHS();
		
		model.addAttribute("list" , list);
		
		return "lophs/show";
	}
	
	@RequestMapping(value = "add")
	public String addLopHS(Model model) {
		
		List<Lop> listLop = lopService.getAllLop();
		
		List<HocSinh> listHS = hocSinhrepo.findAll();
		
		model.addAttribute("listLop" , listLop);
		
		model.addAttribute("listHS" , listHS);
		
		model.addAttribute("lopHS" , new Lop_hs());
		
		return "lophs/add";
	}
	
	@RequestMapping(value = "doSave")
	public String doSave(@ModelAttribute("Lop_hs") Lop_hs lop_hs) {
		
		lop_HSService.save(lop_hs);
		
		return "redirect:show";
	}
	
}
