package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.model.DauDiem;
import com.example.demo.model.KhoaHoc;
import com.example.demo.service.DauDiemService;

@Controller
@RequestMapping(value = "daudiem")
public class DauDiemController {
	
	@Autowired
	DauDiemService dauDiemService;
	
	@GetMapping(value = "showDD")
	public String showDD(Model model) {
		
//		List<DauDiem> daudiem = dauDiemService.getAllDiem();
		
		List<DauDiem> daudiem = dauDiemService.getDauDiemMon();
		
		model.addAttribute("daudiem" , daudiem);
		
		return "daudiem/show";
	}
	
	
	@RequestMapping(value = "addDD")
	public String addDD(Model model) {
	
		List<DauDiem> listdaudiem = dauDiemService.getAllDauDiem();
		System.out.println("absdkajshudilamnsdhuials"+listdaudiem);
		/*
		 * for(DauDiem lixt : listdaudiem) { lixt.getLoaiDauDiem();
		 * System.out.println("huy ngu"+lixt.getLoaiDauDiem()+lixt.getIDMonc().getTenMon
		 * ()); }
		 */
		model.addAttribute("tendaudiem" , listdaudiem);
		
	    model.addAttribute("daudiem" , new DauDiem()); 
		
		return "daudiem/add";
		
	}
	
	@PostMapping(value = "saveDD")
	public String doSaveDD(@ModelAttribute("daudiem") DauDiem daudiem,Model model) {
		System.out.println(daudiem);
		
		dauDiemService.save(daudiem);
		
		return "redirect:showDD";

	}
	
	
	
}
