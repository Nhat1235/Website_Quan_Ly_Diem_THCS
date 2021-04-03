package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.model.GV_Lop_Mon;
import com.example.demo.model.GiaoVien;
import com.example.demo.model.HocKy;
import com.example.demo.model.Lop;
import com.example.demo.model.Lop_hs;
import com.example.demo.model.Mon;
import com.example.demo.service.GV_L_MService;
import com.example.demo.service.GiaoVienService;
import com.example.demo.service.HocKyService;
import com.example.demo.service.LopService;
import com.example.demo.service.MonService;

@Controller
@RequestMapping(value = "gvlm")
public class GV_Lop_MonController {

	@Autowired
	MonService monService;
	
	@Autowired
	GV_L_MService gv_L_MService;

	@Autowired
	GiaoVienService giaoVienService;
	
	@Autowired
	LopService lopService;

	@Autowired
	HocKyService hocKyService;
	
	@GetMapping(value = "show")
	public String show(Model model) {

		List<GV_Lop_Mon> list = gv_L_MService.getAll();

		model.addAttribute("list", list);

		return "GVLM/show";

	}

	@RequestMapping(value = "add")
	public String add(Model model) {

		List<HocKy> hocky = hocKyService.getAllHocKy();
		
		List<Lop> listLop = lopService.getAllLop();

		List<Mon> mon = monService.getAllMon();
		
		List<GiaoVien> giaovien = giaoVienService.getAllGV();
		
		model.addAttribute("hocky",hocky);
		
		model.addAttribute("listLop" , listLop);
		
		model.addAttribute("mon",mon);
		
		model.addAttribute("giaovien",giaovien);
		
		model.addAttribute("gvlm" , new GV_Lop_Mon());
		
		return "GVLM/add";
		
	}
	
	@RequestMapping(value = "doSave")
	public String doSave(@ModelAttribute("GV_L_M") GV_Lop_Mon gv_Lop_Mon) {
		
		gv_L_MService.save(gv_Lop_Mon);
		
		return "redirect:show";
	}
	
	
	

}
