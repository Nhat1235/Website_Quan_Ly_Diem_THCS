package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.model.DauDiem;
import com.example.demo.model.Mon;
import com.example.demo.repositories.MonRepository;
import com.example.demo.repositories.QuanLyDauDiemRepository;

@Controller
public class DauDiemController {

	@Autowired
	QuanLyDauDiemRepository repo;
	
	@Autowired
	MonRepository Monrepo;
	
	@GetMapping("Quan-ly-dau-diem")
	public String qldd(ModelMap model) {
		List<DauDiem> list = repo.findAll();
		model.addAttribute("list", list);
		return "DD";
	}
	
	@GetMapping("Quan-ly-dau-diem/add")
	public String add1(ModelMap model) {
		List<Mon> list = Monrepo.findAll();
		model.addAttribute("list", list);
		model.addAttribute("daudiem", new DauDiem());
		return "addDD";
	}
	
	@PostMapping("Quan-ly-dau-diem/add")
	public String add(@ModelAttribute("daudiem")DauDiem daudiem) {
		repo.save(daudiem);
		return "DD";
	}
}
