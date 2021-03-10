package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.model.Diem;
import com.example.demo.service.DiemService;

@Controller
@RequestMapping(value = "/gv")
public class DiemController {

	@Autowired
	private DiemService diemService;
	
	
	@GetMapping(value = "/show")
	public String showDiem(Model model) {
		List<Diem> diem = diemService.getAllDiem();
		
		model.addAttribute("diem" , diem);
		
		return "gv/show";
	}
	
	@RequestMapping(value = "/add")
	public String addDiem(Model model) {
		
		model.addAttribute("diem" , new Diem());
		return "gv/add";
	}
}
