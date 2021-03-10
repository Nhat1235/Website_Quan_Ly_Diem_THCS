package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.model.KhoaHoc;
import com.example.demo.model.Lop;
import com.example.demo.service.KhoaHocService;
import com.example.demo.service.LopService;

@Controller
@RequestMapping(value = "/lop")
public class LopController {

	@Autowired
	LopService lopService;

	@Autowired
	KhoaHocService khoaHocService;

	@GetMapping(value = "showLop")
	public String showLop(Model model) {
		
		//List<Lop> ten = lopService.getAllLop();
		
		List<Lop> lop = lopService.getLopKhoaHoc();
		
		model.addAttribute("listLop" , lop);
		
		
//		model.addAttribute("namkhoahoc" , khoaHocService.getNamKhoaHoc());
		
		return "lop/show";
		
	}
	
	@RequestMapping(value = "addLop")
	public String addLop(Model model) {
		
		List<KhoaHoc> listKhoahoc = khoaHocService.getAllKhoaHoc();
		
		model.addAttribute("namkhoahoc" , listKhoahoc);
				
		model.addAttribute("lop" , new Lop());
		
		return "lop/add";
	}
	@PostMapping(value = "saveLop")
	public String doSaveLop(@ModelAttribute("Lop") Lop lop,Model model) {
	
		lopService.save(lop);
		
		return "redirect:showLop";
		
	}
	
	

}
