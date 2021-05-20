package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.example.demo.model.HocSinh;
import com.example.demo.model.Lop;
import com.example.demo.model.Lop_hs;
import com.example.demo.model.TaiKhoanGv;
import com.example.demo.repositories.HocSinhRepository;
import com.example.demo.repositories.Lop_HocSinhrepository;
import com.example.demo.repositories.TaiKhoanGvRepository;
import com.example.demo.service.LopService;
import com.example.demo.service.Lop_HSService;

@Controller
@RequestMapping(value = "lophs")
public class Lop_HSController {

	@Autowired
	TaiKhoanGvRepository TKGvRepo;
	
	@Autowired
	Lop_HSService lop_HSService;
	
	@Autowired
	LopService lopService;
	
	@Autowired
	HocSinhRepository hocSinhrepo;
	
	@Autowired
	Lop_HocSinhrepository lhsrepo;
	
	@GetMapping(value = "show")
	public String show(Model model) {
		
		List<Lop_hs> list = lop_HSService.getLopHS();
		
		model.addAttribute("list" , list);
		
		return "lophs/show";
	}
	
	@RequestMapping(value = "add")
	public String addLopHS(Model model) {
		
		List<Lop> listLop = lopService.getAllLop();
		
		List<HocSinh> listHS = hocSinhrepo.findAll();
		
		model.addAttribute("listLop" , listLop);
		
		model.addAttribute("listHS" , listHS);
		
		model.addAttribute("lopHS" , new Lop_hs());
		
		return "lophs/add";
	}
	
	@RequestMapping(value = "doSave")
	public String doSave(@ModelAttribute("Lop_hs") Lop_hs lop_hs) {
		
		lop_HSService.save(lop_hs);
		
		return "redirect:show";
	}
	
	@GetMapping("deleteLopHS/{idlophs}/{idlop}")
	public RedirectView delete(@PathVariable("idlophs")Integer idlophs, @PathVariable("idlop")String idlop) {
	   System.out.println(idlophs);
	   
	   lhsrepo.updateLHS(idlophs);
		
	   return new RedirectView("/lophs/showLop/"+idlop);
	}
	
	@GetMapping("addtoLopHS/{idlophs}/{idlop}")
	public RedirectView addtolop(@PathVariable("idlophs")Integer idlophs, @PathVariable("idlop")Integer idlop) {
	   System.out.println(idlophs);
	   
	   lhsrepo.updateLHS2(idlophs,idlop);
		
	   return new RedirectView("/lophs/showLop/"+idlop);
	}
	
	
	@RequestMapping(value = "showLop/{idlop}")
	public String showlop(@PathVariable("idlop")Integer idlop, Model model, Authentication authentication) {
		TaiKhoanGv tkgv = TKGvRepo.findAllDetail(authentication.getName());
		
		List<Lop_hs> list = lhsrepo.findByIdLop(idlop);
		
		List<Lop_hs> list2 = lhsrepo.findHSWithoutLop();
		
		model.addAttribute("list2", list2);
		model.addAttribute("tkgv" , tkgv);
		model.addAttribute("list", list);
		model.addAttribute("idlop", idlop);
		
		return "lophs/showLop";
	}
	
	
}
