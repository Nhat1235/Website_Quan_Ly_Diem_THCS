package com.example.demo.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.helper.MyUploadForm;
import com.example.demo.model.DauDiem;
import com.example.demo.model.Diem;
import com.example.demo.model.GV_Lop_Mon;
import com.example.demo.model.GiaoVien;
import com.example.demo.model.HocKy;
import com.example.demo.model.KhoaHoc;
import com.example.demo.model.Lop_hs;
import com.example.demo.model.Mon;
import com.example.demo.model.TaiKhoanGv;
import com.example.demo.repositories.DauDiemRepository;
import com.example.demo.repositories.DiemRepository;
import com.example.demo.repositories.GiaoVienRepository;
import com.example.demo.repositories.HocKyRepository;
import com.example.demo.repositories.HocSinh_LopRepository;
import com.example.demo.repositories.KhoaHocRepository;
import com.example.demo.repositories.MonRepository;
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
	
	@RequestMapping("GV/findKH")
	public String findkh(ModelMap model, @RequestParam("cbb") String keyword , @RequestParam("cbb2") String keyword2, Authentication authentication) {

		System.out.println("combobox value: " + keyword + ""+ keyword2);
		
		// Combobox khóa học
		List<KhoaHoc> kh = khrepo.findAll();

		//Combobox Học kỳ
		List<HocKy> hk = hkrepo.findAllHK();
		
		for (HocKy list2 : hk) {
			System.out.println(list2.getTenhocky());
		}
		
		List<KhoaHoc> khoahoc1 = khrepo.findAll(keyword);
		
		System.out.println(khoahoc1);
		
		TaiKhoanGv tkgv = TKGvRepo.findAllDetail(authentication.getName());

		String lop = "";

		List<GV_Lop_Mon> list = repo.FindLopByKhoahoc(tkgv.getGiaovienfk().getTen(),keyword,keyword2);
		for (GV_Lop_Mon list2 : list) {
			lop = list2.getIDLop().getTenlop();
		}

		System.out.println("Print Data:" + tkgv.getGiaovienfk().getTen() + " " + authentication.getName() + " Lop:"
				+ lop + " IdGVLM:" + tkgv.getId());
//				List<DauDiem> DDlist = rep.getDauDiemByLop(1);
		List<Diem> diemlist = DRepo.findDiemLop(lop);

		List<Lop_hs> hsllist = hslrepo.getHS(lop);

		
		model.addAttribute("hk", hk);
		model.addAttribute("kh", kh);
		model.addAttribute("khoahoc1", khoahoc1);
		model.addAttribute("hsllist", hsllist);
		model.addAttribute("diemlist", diemlist);
		model.addAttribute("tkgv", tkgv);
		model.addAttribute("list", list);
		return "examples/table";

	}

	@GetMapping("GV/GVBM")
	public String slddC(ModelMap model, Authentication authentication) {

		// Combobox khóa học
		List<KhoaHoc> kh = khrepo.findAll();

		//Combobox Học kỳ
		List<HocKy> hk = hkrepo.findAll();
				
		TaiKhoanGv tkgv = TKGvRepo.findAllDetail(authentication.getName());

		String lop = "";

		List<GV_Lop_Mon> list = repo.FindLopDangDay(tkgv.getGiaovienfk().getTen());
		for (GV_Lop_Mon list2 : list) {
			lop = list2.getIDLop().getTenlop();
		}

		System.out.println("Print Data:" + tkgv.getGiaovienfk().getTen() + " " + authentication.getName() + " Lop:" + lop + " IdGVLM:" + tkgv.getId());
//		List<DauDiem> DDlist = rep.getDauDiemByLop(1);
		List<Diem> diemlist = DRepo.findDiemLop(lop);

		List<Lop_hs> hsllist = hslrepo.getHS(lop);

		
		model.addAttribute("hk", hk);
		model.addAttribute("kh", kh);
		model.addAttribute("hsllist", hsllist);
		model.addAttribute("diemlist", diemlist);
		model.addAttribute("tkgv", tkgv);
		model.addAttribute("list", list);
		return "examples/table";
	}

	@GetMapping("/")
	public String slddC21(ModelMap model, Authentication authentication) {
		TaiKhoanGv tkgv = TKGvRepo.findAllDetail(authentication.getName());

		String lop = "";

		List<GV_Lop_Mon> list = repo.FindLopDangDay(tkgv.getGiaovienfk().getTen());
		for (GV_Lop_Mon list2 : list) {
			lop = list2.getIDLop().getTenlop();
		}

		System.out.println("Print Data:" + tkgv.getGiaovienfk().getTen() + " " + authentication.getName() + " Lop:"
				+ lop + " IdGVLM:" + tkgv.getId());

		model.addAttribute("tkgv", tkgv);
		model.addAttribute("list", list);
		return "examples/user";
	}

	@GetMapping("")
	public String slddC2(ModelMap model, Authentication authentication) {
		TaiKhoanGv tkgv = TKGvRepo.findAllDetail(authentication.getName());

		String lop = "";

		List<GV_Lop_Mon> list = repo.FindLopDangDay(tkgv.getGiaovienfk().getTen());
		for (GV_Lop_Mon list2 : list) {
			lop = list2.getIDLop().getTenlop();
		}

		System.out.println("Print Data:" + tkgv.getGiaovienfk().getTen() + " " + authentication.getName() + " Lop:"
				+ lop + " IdGVLM:" + tkgv.getId());

		List<Diem> diemlist = DRepo.findDiemLop(lop);

		List<Lop_hs> hsllist = hslrepo.getHS(lop);

		model.addAttribute("hsllist", hsllist);
		model.addAttribute("diemlist", diemlist);
		model.addAttribute("list", list);
		return "Giao_Vien_Bo_Mon";
	}

	@GetMapping("GVBM/show/{lop}/{idM}/{idGVLM}")
	public String slddC2(ModelMap model, Authentication authentication, @PathVariable("lop") String lop,
			@PathVariable("idM") Integer idM, @PathVariable("idGVLM") Integer idGVLM) {
		TaiKhoanGv tkgv = TKGvRepo.findAllDetail(authentication.getName());

		MyUploadForm myUploadForm = new MyUploadForm();
		model.addAttribute("myUploadForm", myUploadForm);

		Mon mon = monRep.findTenMonByIDMon(idM);

		List<Diem> diemlist = DRepo.findDiemLop(lop);

		List<Lop_hs> hsllist = hslrepo.getHS(lop);

		List<DauDiem> DDlist = rep.getDauDiemByMon(idM);

		model.addAttribute("idGVLM", idGVLM);
		model.addAttribute("mon", mon);
		model.addAttribute("tkgv", tkgv);
		model.addAttribute("DDlist", DDlist);
		model.addAttribute("hsllist", hsllist);
		model.addAttribute("idM", idM);
		model.addAttribute("diemlist", diemlist);
		return "GVBM/show1";

	}

}
