package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.model.DauDiem;
import com.example.demo.model.Diem;
import com.example.demo.model.GV_Lop_Mon;
import com.example.demo.model.Lop_hs;
import com.example.demo.repositories.QuanLyDauDiemRepository;
import com.example.demo.service.DiemService;
import com.example.demo.service.GV_L_MService;
import com.example.demo.service.Lop_HSService;

@Controller
@RequestMapping(value = "diem")
public class DiemController {

	@Autowired
	private DiemService diemService;

	@Autowired
	GV_L_MService gv_L_MService;
	
	@Autowired
	Lop_HSService lop_HSService;
	

	@Autowired
	QuanLyDauDiemRepository repo;
	
	@GetMapping(value = "/show")
	public String showDiem(Model model) {
		
		List<Diem> diem = diemService.getAll();
		
		model.addAttribute("diem" , diem);
		
		return "diem/show";
	}
	
	@RequestMapping(value = "/add")
	public String addDiem(Model model) {
		
		List<Lop_hs> lophs = lop_HSService.getLopHS();
		
		List<DauDiem> daudiem = repo.findAll();
		
		List<GV_Lop_Mon> GVLM = gv_L_MService.getAll();
		
		model.addAttribute("lophs",lophs);
		
		model.addAttribute("daudiem" , daudiem);
		
		model.addAttribute("GVLM" , GVLM);
		
		model.addAttribute("diem" , new Diem());
		
		return "diem/add";
	}
	
	@RequestMapping(value = "doSave")
	public String doSaveDiem(@ModelAttribute("Diem") Diem diem  ) {
		
		diemService.save(diem);
		
		return "redirect:show";
	}
	
}
