package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.model.GV_Lop_Mon;
import com.example.demo.model.KhoaHoc;
import com.example.demo.model.Lop;
import com.example.demo.model.Lop_hs;
import com.example.demo.model.TaiKhoanGv;
import com.example.demo.repositories.GV_L_MRepository;
import com.example.demo.repositories.Lop_HocSinhrepository;
import com.example.demo.repositories.TaiKhoanGvRepository;
import com.example.demo.service.KhoaHocService;
import com.example.demo.service.LopService;

@Controller
@RequestMapping(value = "/lop")
public class LopController {

	@Autowired
	LopService lopService;

	@Autowired
	KhoaHocService khoaHocService;

	@Autowired
	TaiKhoanGvRepository TKGvRepo;
	
	@Autowired
	GV_L_MRepository gvlmrepo;
	
	@Autowired
	Lop_HocSinhrepository lhsrepo;
	
	
	@GetMapping(value = "showLop")
	public String showLop(Model model, Authentication authentication) {
		
		TaiKhoanGv tkgv = TKGvRepo.findAllDetail(authentication.getName());
		
		List<KhoaHoc> listKhoahoc = khoaHocService.getAllKhoaHoc();
		
		List<Lop> lop = lopService.getLopKhoaHoc();
		
		model.addAttribute("tkgv" , tkgv);
		
		model.addAttribute("listLop" , lop);
		
		model.addAttribute("lop" , new Lop());
		
		model.addAttribute("namkhoahoc" , listKhoahoc);
		
		return "lop/show";
		
	}
	
	@GetMapping(value = "/showDetails/{tenlop}/{khoahoc}")
	public String showDetails(Model model, Authentication authentication, @PathVariable("tenlop")String tenlop, @PathVariable("khoahoc")String khoahoc) {
		TaiKhoanGv tkgv = TKGvRepo.findAllDetail(authentication.getName());
		List<GV_Lop_Mon> list = gvlmrepo.findLopDetails(tenlop, khoahoc);
		
		String idlop = "";
		String khoahocStirng = "";
		for(int i=0; i<list.size();i++) {
			idlop = list.get(i).getIDLop().getIdlop().toString();
			khoahocStirng = list.get(i).getIDHocKy().getNam().toString();
		}
		
		List<Lop_hs> listlhs = lhsrepo.getLopHS(tenlop);
		
		
		model.addAttribute("list", listlhs);
		model.addAttribute("tkgv" , tkgv);
		model.addAttribute("khoahocStirng" , khoahocStirng);
		
		model.addAttribute("khoahoc" , khoahoc);
		System.out.println(list);
		return "lop/showDetail";
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
