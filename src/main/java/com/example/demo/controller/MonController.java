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
import com.example.demo.model.Mon;
import com.example.demo.service.MonService;

@Controller
@RequestMapping(value = "mon")
public class MonController {
	
	@Autowired
	MonService monService;
	
	@GetMapping(value = "showMon")
	public String showMon(Model model) {

		List<Mon> mon = monService.getAllMon();

		model.addAttribute("mon", mon);

		return "mon/show";

	}

	
	@RequestMapping(value = "addMon")
	public String addMon(Model model) {
		
		model.addAttribute("mon" , new Mon());
		
		return "mon/add";
	}
	@PostMapping(value = "saveMon")
	public String doSaveMon(@ModelAttribute("Mon") Mon mon,Model model) {
	
		monService.save(mon);
		
		return "redirect:showMon";
		
	}
	
	
	
	
	
}
