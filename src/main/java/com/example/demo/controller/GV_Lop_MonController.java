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

import com.example.demo.model.GV_Lop_Mon;
import com.example.demo.model.GiaoVien;
import com.example.demo.model.HocKy;
import com.example.demo.model.Lop;
import com.example.demo.model.Lop_hs;
import com.example.demo.model.Mon;
import com.example.demo.model.TaiKhoanGv;
import com.example.demo.repositories.GV_L_MRepository;
import com.example.demo.repositories.LopRepository;
import com.example.demo.repositories.TaiKhoanGvRepository;
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
	GV_L_MRepository rep;
	
	@Autowired
	LopRepository repo;
	
	@Autowired
	GV_L_MService gv_L_MService;

	@Autowired
	GiaoVienService giaoVienService;
	
	@Autowired
	LopService lopService;

	@Autowired
	HocKyService hocKyService;
	
	@Autowired
	GV_L_MRepository gvlmRepo;
	
	@Autowired
	TaiKhoanGvRepository TKGvRepo;
	
	@GetMapping(value = "show/{tenlop}/{khoahoc}")
	public String show(Model model, @PathVariable("tenlop")String tenlop, @PathVariable("khoahoc")String khoahoc, Authentication authentication) {
 
		TaiKhoanGv tkgv = TKGvRepo.findAllDetail(authentication.getName());
		
		List<GV_Lop_Mon> list = gv_L_MService.getAll();
        
		List<GV_Lop_Mon> listlop = gvlmRepo.findLopDetails(tenlop, khoahoc);
		
		model.addAttribute("listlop", listlop);
		
		model.addAttribute("tkgv", tkgv);
		
        List<HocKy> hocky = hocKyService.getAllHocKy();
		
		List<Lop> listLop = lopService.getAllLop();

		List<Lop> getLop = repo.getLop(tenlop);
		
		List<Mon> mon = monService.getAllMon();
		
		List<GiaoVien> giaovien = giaoVienService.getAllGV();
		
		model.addAttribute("hocky",hocky);
		
		model.addAttribute("listLop" , listLop);
		
		model.addAttribute("listLop1" , getLop);
		
		model.addAttribute("mon",mon);
		
		model.addAttribute("giaovien",giaovien);
		
		model.addAttribute("gvlm" , new GV_Lop_Mon());
		
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
	
	@RequestMapping(value = "doSave/{khoahoc}")
	public String doSave(@PathVariable("khoahoc")String kh, @ModelAttribute("GV_L_M") GV_Lop_Mon gv_Lop_Mon, Authentication authentication, Model model) {
		
		String a = gv_Lop_Mon.getIDLop().getIdlop().toString();
		
		String tenlop = gv_Lop_Mon.getIDLop().getTenlop();
		String tenhk = gv_Lop_Mon.getIDHocKy().getIdhocky().toString();
		String monhoc = gv_Lop_Mon.getIDMon().getTenMon();
		
		System.out.println(monhoc+"/"+tenhk+"/"+tenlop);
		
		List<GV_Lop_Mon> count = rep.countLopDetails(tenlop, tenhk, monhoc);
		System.out.println(count);
		
		if(count.size()>0) {
			System.out.println("đã có rồi!");
			model.addAttribute("error", "*Bộ môn này đã có người dạy, vui lòng chọn bộ môn khác!");
		}else {
			gv_L_MService.save(gv_Lop_Mon);
		}
		
		
		TaiKhoanGv tkgv = TKGvRepo.findAllDetail(authentication.getName());
		
		List<GV_Lop_Mon> list = gv_L_MService.getAll();
        
		List<GV_Lop_Mon> listlop = gvlmRepo.findLop(Integer.parseInt(a));
		
		model.addAttribute("listlop", listlop);
		
		model.addAttribute("tkgv", tkgv);
		
        List<HocKy> hocky = hocKyService.getAllHocKy();
		
		List<Lop> listLop = lopService.getAllLop();

		List<Mon> mon = monService.getAllMon();
		
		List<GiaoVien> giaovien = giaoVienService.getAllGV();
		
		model.addAttribute("hocky",hocky);
		
		model.addAttribute("listLop" , listLop);
		
		model.addAttribute("mon",mon);
		
		model.addAttribute("giaovien",giaovien);
		
		model.addAttribute("gvlm" , new GV_Lop_Mon());
		
		return "redirect:/gvlm/show/"+tenlop+"/"+kh;
	}
	
	
	

}
