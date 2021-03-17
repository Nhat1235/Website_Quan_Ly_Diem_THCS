package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.model.Diem;
import com.example.demo.model.GV_Lop_Mon;
import com.example.demo.model.GiaoVien;
import com.example.demo.model.Lop_hs;
import com.example.demo.model.TaiKhoanGv;
import com.example.demo.repositories.DiemRepository;
import com.example.demo.repositories.GiaoVienRepository;
import com.example.demo.repositories.HocSinh_LopRepository;
import com.example.demo.repositories.ShowLopDangDayRepository;
import com.example.demo.repositories.TaiKhoanGvRepository;

@Controller
public class ShowLopDangDayController {
    
	@Autowired
	ShowLopDangDayRepository repo;
	
	@Autowired
	TaiKhoanGvRepository TKGvRepo;
	
	@Autowired
	DiemRepository DRepo;
	
	@Autowired
	HocSinh_LopRepository hslrepo;
	
	@GetMapping("GVBM")
	public String slddC(ModelMap model,Authentication authentication) {
		TaiKhoanGv tkgv = TKGvRepo.findAllDetail(authentication.getName());

		String lop="";
		List<GV_Lop_Mon> list = repo.FindLopDangDay(tkgv.getGiaovienfk().getTen());
		for(GV_Lop_Mon list2 : list) {
			lop = list2.getIDLop().getTenlop();
		}
		System.out.println("Print Data:"+tkgv.getGiaovienfk().getTen()+" "+authentication.getName()+" Lop:"+lop);
		
		List<Diem> diemlist = DRepo.findDiemLop(lop);
		
		List<Lop_hs> hsllist = hslrepo.getHS();
		
		model.addAttribute("hsllist", hsllist);
		model.addAttribute("diemlist", diemlist);
		model.addAttribute("list", list);
		return "GVBM";
	}
}
