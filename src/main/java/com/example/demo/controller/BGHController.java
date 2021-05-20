package com.example.demo.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.model.DauDiem;
import com.example.demo.model.GiaoVien;
import com.example.demo.model.HocSinh;
import com.example.demo.model.Lop;
import com.example.demo.model.Lop_hs;
import com.example.demo.model.TaiKhoanGv;
import com.example.demo.model.TaiKhoanHs;
import com.example.demo.repositories.GiaoVienRepository;
import com.example.demo.repositories.HocSinhRepository;
import com.example.demo.repositories.LopRepository;
import com.example.demo.repositories.Lop_HocSinhrepository;
import com.example.demo.repositories.TaiKhoanGvRepository;
import com.example.demo.repositories.TaiKhoanHSRepository;

@Controller
@RequestMapping(value = "BGH")
public class BGHController {

	@Autowired
	TaiKhoanGvRepository taiKhoanGvRepository;
	
	@Autowired
	TaiKhoanHSRepository taiKhoanHSRepository;
	
	@Autowired
	HocSinhRepository hocsinhrepo;
	
	@Autowired
	GiaoVienRepository gvrepo;
	
	@Autowired
	Lop_HocSinhrepository lhsrep;
	
	@Autowired
	LopRepository lhs;
	
	@GetMapping
	public String showTaiKhoanGV(Model model , Authentication authentication,TaiKhoanHs TKHS,TaiKhoanGv TKGV ) {
		
		TaiKhoanGv ten = taiKhoanGvRepository.findAllDetail(authentication.getName());
		
		List<TaiKhoanGv> tkgv= taiKhoanGvRepository.findAll();
		
		List<TaiKhoanHs> tkhs = taiKhoanHSRepository.findAll();
		
		model.addAttribute("tkgv",tkgv);
		
		model.addAttribute("tkhs",tkhs);
		
		model.addAttribute("ten",ten);
		
		model.addAttribute("TKGV", new TaiKhoanGv());
		
		model.addAttribute("TKHS", new TaiKhoanHs());
		return "BGH";
		
	}
	@PostMapping("addTKGV")
	public String add(@Valid @ModelAttribute("TKGV")TaiKhoanGv TKGV, BindingResult bindingResult) {
        	
		GiaoVien gv = new GiaoVien();
		gv.setAnhGV("default.png");
		gv.setChucvu(TKGV.getChucvu());
		gv.setTen(TKGV.getTentk());
		
		gvrepo.save(gv);
		List<GiaoVien> list = gvrepo.findID(TKGV.getTentk(), TKGV.getChucvu());
		
		GiaoVien a = new GiaoVien();
		for(int i =0; i<list.size(); i++) {
		 a = list.get(i);
		}
		
		
		System.out.println(TKGV.getTentk());
		System.out.println(TKGV.getMatkhau());
		System.out.println(TKGV.getChucvu());
		TKGV.setGiaovienfk(a);
		System.out.println(a);
		System.out.println("AA: "+ TKGV.getGiaovienfk());
		taiKhoanGvRepository.save(TKGV);
		
		
		
		return "redirect:";
        }
	
	
	@PostMapping("addTKHS")
	public String addhs(@ModelAttribute("TKHS")TaiKhoanHs TKHS) {
		
		HocSinh hs = new HocSinh();
		hs.setAnhHS("defaultHS.png");
		hs.setTenhocsinh(TKHS.getTentk());
		
		hocsinhrepo.save(hs);
		List<HocSinh> list = hocsinhrepo.findID(TKHS.getTentk());
		
		HocSinh a = new HocSinh();
		HocSinh idhs = new HocSinh();
		for(int i =0; i<list.size(); i++) {
		 a = list.get(i);
		 idhs = list.get(i);
		}
		System.out.println(TKHS.getTentk());
		System.out.println(TKHS.getMatkhau());
		System.out.println(TKHS.getChucvu());
		TKHS.setChucvu("HS");
		TKHS.setHocsinhfk(a);
		
		taiKhoanHSRepository.save(TKHS);
		
		List<Lop> lop = lhs.getLop("Chưa có lớp");
		
		Lop lopi = new Lop();
		for(int ii =0; ii<list.size(); ii++) {
			lopi = lop.get(ii);
		}
		Lop_hs lhs = new Lop_hs();
		lhs.setIdLopc(lopi);
		lhs.setIdhs(idhs);
		
		lhsrep.save(lhs);
		
		return "redirect:";
	}
}
