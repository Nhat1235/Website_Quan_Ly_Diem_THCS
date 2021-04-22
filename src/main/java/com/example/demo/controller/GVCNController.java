package com.example.demo.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.model.DauDiem;
import com.example.demo.model.Diem;
import com.example.demo.model.GV_Lop_Mon;
import com.example.demo.model.HocKy;
import com.example.demo.model.KhoaHoc;
import com.example.demo.model.Lop_hs;
import com.example.demo.model.Mon;
import com.example.demo.model.TaiKhoanGv;
import com.example.demo.repositories.DauDiemRepository;
import com.example.demo.repositories.DiemRepository;
import com.example.demo.repositories.GV_L_MRepository;
import com.example.demo.repositories.GiaoVienRepository;
import com.example.demo.repositories.HocKyRepository;
import com.example.demo.repositories.HocSinh_LopRepository;
import com.example.demo.repositories.KhoaHocRepository;
import com.example.demo.repositories.MonRepository;
import com.example.demo.repositories.ShowLopDangDayRepository;
import com.example.demo.repositories.TaiKhoanGvRepository;

@Controller
public class GVCNController {

	@Autowired
	ShowLopDangDayRepository repo;

	@Autowired
	TaiKhoanGvRepository TKGvRepo;

	@Autowired
	DiemRepository DRepo;

	@Autowired
	HocSinh_LopRepository hslrepo;

	@Autowired
	DauDiemRepository rep;

	@Autowired
	GiaoVienRepository gvRep;

	@Autowired
	MonRepository monRep;

	@Autowired
	KhoaHocRepository khrepo;

	@Autowired
	HocKyRepository hkrepo;
	
	@Autowired
	GV_L_MRepository gvlmrepo;
	
	
	@RequestMapping("GV/GVCN/findKH")
	public String findkh(ModelMap model, @RequestParam("cbb") String keyword , @RequestParam("cbb2") String keyword2, Authentication authentication) {

		System.out.println("combobox value: " + keyword + " (and) "+ keyword2);
		
		// Combobox khóa học
		List<KhoaHoc> kh = khrepo.findAll();

		//Combobox Học kỳ
		List<HocKy> hk = hkrepo.findAllHK();
		
		for (HocKy list2 : hk) {
			System.out.println(list2.getTenhocky());
		}
		
		List<KhoaHoc> khoahoc1 = khrepo.findAll(keyword);
		
		TaiKhoanGv tkgv = TKGvRepo.findAllDetail(authentication.getName());

		String lop = "";
		String lopName="";
		List<GV_Lop_Mon> list = repo.FindLopByKhoahoc(tkgv.getGiaovienfk().getTen(),keyword,keyword2);
		for (GV_Lop_Mon list2 : list) {
			lop = list2.getIDLop().getIdlop().toString();
			lopName = list2.getIDLop().getTenlop();
		}

		System.out.println("Print Data:" + tkgv.getGiaovienfk().getTen() + " " + authentication.getName() + " Lop:"
				+ lop + " IdGVLM:" + tkgv.getId());

		List<Lop_hs> lophslist = hslrepo.findByIDlop(lop);
				
		List<Diem> diemlist = DRepo.findDiemLop(lop);

		List<Lop_hs> hsllist = hslrepo.getHS(lop);

		model.addAttribute("lop", lopName);
		model.addAttribute("lophslist", lophslist);
		model.addAttribute("hk", hk);
		model.addAttribute("kh", kh);
		model.addAttribute("khoahoc1", khoahoc1);
		model.addAttribute("hsllist", hsllist);
		model.addAttribute("diemlist", diemlist);
		model.addAttribute("tkgv", tkgv);
		model.addAttribute("list", list);
		return "GVCN/show";

	}

	@GetMapping("GV/GVCN")
	public String slddC(ModelMap model, Authentication authentication) {

		// Combobox khóa học
		List<KhoaHoc> kh = khrepo.findAll();

		//Combobox Học kỳ
		List<HocKy> hk = hkrepo.findAll();
				
		TaiKhoanGv tkgv = TKGvRepo.findAllDetail(authentication.getName());

		String lop = "";
		String lopName="";
		Integer idM = null;
		String idgvlm="";
		List<GV_Lop_Mon> list = repo.FindLopDangDay(tkgv.getGiaovienfk().getTen());
		for (GV_Lop_Mon list2 : list) {
			lop = list2.getIDLop().getIdlop().toString();
			lopName = list2.getIDLop().getTenlop();
			idM = list2.getIDMon().getIdMon();
			idgvlm=list2.getIdGv_L_M().toString();
			
		}

		List<Lop_hs> lophslist = hslrepo.findByIDlop(lop);
		
		System.out.println(lop+" "+lopName+" "+idM+" "+idgvlm);
		System.out.println("Print Data:" + tkgv.getGiaovienfk().getTen() + " " + authentication.getName() + " Lop:" + lop + " IdGVLM:" + tkgv.getId());
//		List<DauDiem> DDlist = rep.getDauDiemByLop(1);
		List<Diem> diemlist = DRepo.findDiemLop(lop);

		List<Lop_hs> hsllist = hslrepo.getHS(lop);

		model.addAttribute("lop", lopName);
		model.addAttribute("lophslist", lophslist);
		model.addAttribute("idM", idM);	
		model.addAttribute("idgvlm", idgvlm);
		model.addAttribute("hk", hk);
		model.addAttribute("kh", kh);
		model.addAttribute("hsllist", hsllist);
		model.addAttribute("diemlist", diemlist);
		model.addAttribute("tkgv", tkgv);
		model.addAttribute("list", list);
		return "GVCN/show";
	}
	
	@GetMapping("GV/GVCN/show/{lop}/{idM}/{idGVLM}/{idhs}")
	public String slddC2(ModelMap model, Authentication authentication, @PathVariable("lop") String lop,
			@PathVariable("idM") Integer idM, @PathVariable("idGVLM") String idGVLM,@PathVariable("idhs") String idhs) {
		
		TaiKhoanGv tkgv = TKGvRepo.findAllDetail(authentication.getName());
		
		
		System.out.println(idhs);
		List<GV_Lop_Mon> gvlmlist = gvlmrepo.findHS(idhs.trim());
		
		String idMon="";

		for (int i = 0; i < gvlmlist.size(); i++) {
			idMon=gvlmlist.get(i).getIDMon().getIdMon().toString();
		}
		System.out.println(idMon);
		List<Diem> diemlistByid = new ArrayList<>();

		Map<String, List<Diem>> l = new HashMap();
		
		for (int i = 0; i < gvlmlist.size(); i++) {
			
			diemlistByid = DRepo.findDiemByIdHSIdMon(gvlmlist.get(i).getIDMon().getIdMon().toString().trim(), idhs);

			for (int j = 0; j < diemlistByid.size(); j++) {
				l.put(gvlmlist.get(i).getIDMon().getTenMon().toString(), diemlistByid);

			}
			
			for (Map.Entry<String, List<Diem>> entry : l.entrySet()) {
				System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue());
				model.addAttribute("l", l);
			}
       
		}
		String tenhs="";
		for (int i = 0; i < diemlistByid.size(); i++) {
			
			tenhs=diemlistByid.get(i).getIDLopHS().getIdhs().getTenhocsinh();
			System.out.println(tenhs);
		}
		model.addAttribute("tenhs", tenhs);
		model.addAttribute("tkgv", tkgv);
		model.addAttribute("l", l);
		model.addAttribute("diemlistByid", diemlistByid);
		return "GVCN/ShowDiem";
	}
}
