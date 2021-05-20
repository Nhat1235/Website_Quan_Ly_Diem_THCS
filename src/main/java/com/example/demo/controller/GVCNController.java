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
import com.example.demo.model.DiemTB;
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

	@GetMapping("GV/GVCN/{idmon}")
	public String slddC(ModelMap model, Authentication authentication,@PathVariable("idmon")Integer idmon) {

		// Combobox khóa học
		List<KhoaHoc> kh = khrepo.findAll();

		//Combobox Học kỳ
		List<HocKy> hk = hkrepo.findAll();
				
		TaiKhoanGv tkgv = TKGvRepo.findAllDetail(authentication.getName());

		String lop = "";
		String lopName="";
		Integer idM = null;
		String idgvlm="";
		
		List<GV_Lop_Mon> list = repo.FindLopDangDay1(tkgv.getGiaovienfk().getTen(),idmon);
		for (GV_Lop_Mon list2 : list) {
			
			lop = list2.getIDLop().getIdlop().toString();
			lopName = list2.getIDLop().getTenlop();
			idM = list2.getIDMon().getIdMon();
			idgvlm=list2.getIdGv_L_M().toString();
			
		}

		List<Lop_hs> lophslist = hslrepo.findByIDlop(lop);
		
		System.out.println(lop+" "+lopName+" "+idM+" "+idgvlm);
		System.out.println("Print Data:" + tkgv.getGiaovienfk().getTen() + " " + authentication.getName() + " Lop:" + lop + " IdGVLM:" + tkgv.getId());

		List<Diem> diemlist = DRepo.findDiemLop(lop);

		List<Lop_hs> hsllist = hslrepo.getHS(lop);
		
		List<DiemTB> diemTBlist = new ArrayList<>();
		Map<String, List<DiemTB>> l = new HashMap();
		for (int ii = 0; ii < lophslist.size(); ii++) {
			
			String idhs = lophslist.get(ii).getIdhs().getIdHocsinh().toString();
			List<GV_Lop_Mon> gvlmlist = gvlmrepo.findHS(idhs.trim());
			List<Diem> diemlistByid = new ArrayList<>();

			Double tempHS1=0.0;
			Double tempHS2=0.0;
			Double tempHS3=0.0;
			Double tempTBM=0.0;
			
			for (int i = 1; i < gvlmlist.size(); i++) {
				
				diemlistByid = DRepo.findDiemByIdHSIdMon(gvlmlist.get(i).getIDMon().getIdMon().toString().trim(), idhs);
				for (int j = 0; j < diemlistByid.size(); j++) {
					
					if(diemlistByid.get(j).getIDDauDiem().getLoaiDauDiem().equalsIgnoreCase("Miệng") ||diemlistByid.get(j).getIDDauDiem().getLoaiDauDiem().equalsIgnoreCase("15 phút")) {
						System.out.println(diemlistByid.get(j).getIDDauDiem().getLoaiDauDiem() +" = "+diemlistByid.get(j).getDiem());
						
						tempHS1= tempHS1 + diemlistByid.get(j).getDiem();
						System.out.println("Điểm hs 1: "+tempHS1);
					}
					
					if(diemlistByid.get(j).getIDDauDiem().getLoaiDauDiem().equalsIgnoreCase("1 Tiết")) {
						System.out.println(diemlistByid.get(j).getIDDauDiem().getLoaiDauDiem() +" = "+diemlistByid.get(j).getDiem());
						
						tempHS2= tempHS2 + diemlistByid.get(j).getDiem()*2;
						System.out.println("Điểm hs 2: "+tempHS2);
					}
					
					if(diemlistByid.get(j).getIDDauDiem().getLoaiDauDiem().equalsIgnoreCase("Học Kỳ")) {
						System.out.println(diemlistByid.get(j).getIDDauDiem().getLoaiDauDiem() +" = "+diemlistByid.get(j).getDiem());
						
						tempHS3= tempHS3 + diemlistByid.get(j).getDiem()*3;
						System.out.println("Điểm hs 3: "+tempHS3);
					}
					
				}
				
				tempTBM= (tempHS1+tempHS2+tempHS3)/(7+2+2+3);
				System.out.println("Điểm trung bình: "+tempTBM);
				DiemTB d = new DiemTB();
				d.setDiemTB(tempTBM);
							
				diemTBlist.add(d);
				for (int jj = 0; jj < diemlistByid.size(); jj++) {
					
					l.put(gvlmlist.get(i).getIDMon().getTenMon().toString(), diemTBlist);
	                
				}
				
				System.out.println("tb: "+tempTBM);
				
				tempHS3=0.0;
				tempHS2=0.0;
				tempHS1 = 0.0;
				tempTBM=0.0;
				
				for (Map.Entry<String,List<DiemTB>> entry : l.entrySet()) {
					System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue());
					
				}
	       
			}
			
		}
		
		for (Map.Entry<String,List<DiemTB>> entry : l.entrySet()) {
			System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue());
			
		}
		
		
		model.addAttribute("l", l);
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
	
	@GetMapping("/showDiemDetail/{idhs}")
	public String slddC211(ModelMap model, Authentication authentication,@PathVariable("idhs") String idhs) {
		
		TaiKhoanGv tkgv = TKGvRepo.findAllDetail(authentication.getName());
		
		
		System.out.println(idhs);
		List<GV_Lop_Mon> gvlmlist = gvlmrepo.findHS(idhs.trim());
		
		String idMon="";
		String hk="";
		String lop="";
		for (int i = 0; i < gvlmlist.size(); i++) {
			idMon=gvlmlist.get(i).getIDMon().getIdMon().toString();
			hk=gvlmlist.get(i).getIDHocKy().getTenhocky();
			lop=gvlmlist.get(i).getIDLop().getTenlop();
			System.out.println("a: "+gvlmlist.size());
		}
		
		List<Diem> diemlistByid = new ArrayList<>();

		Map<String, List<Diem>> l = new HashMap();
		
		Double tempHS1=0.0;
		
		String tenhs="";
		String lophs="";
		String anh= "";
		
		for (int i = 0; i < gvlmlist.size(); i++) {
			
			diemlistByid = DRepo.findDiemByIdHSIdMon(gvlmlist.get(i).getIDMon().getIdMon().toString().trim(), idhs);
			for (int j = 0; j < diemlistByid.size(); j++) {
				
				if(diemlistByid.get(j).getIDDauDiem().getLoaiDauDiem().equalsIgnoreCase("Miệng") ||diemlistByid.get(j).getIDDauDiem().getLoaiDauDiem().equalsIgnoreCase("15 phút") ) {
					System.out.println(diemlistByid.get(j).getIDDauDiem().getLoaiDauDiem() +" = "+diemlistByid.get(j).getDiem());
					
					tempHS1= tempHS1 + diemlistByid.get(j).getDiem();
					System.out.println("temp: "+tempHS1);
				}
			}
			tempHS1=0.0;
			//add vào Map: 
			for (int j = 0; j < diemlistByid.size(); j++) {
				l.put(gvlmlist.get(i).getIDMon().getTenMon().toString(), diemlistByid);
                
			}
			
			for (Map.Entry<String, List<Diem>> entry : l.entrySet()) {
				System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue().get(i).getDiem());
				model.addAttribute("l", l);
			}
       
			

			for (int ii = 0; ii < diemlistByid.size(); ii++) {
				
				tenhs=diemlistByid.get(ii).getIDLopHS().getIdhs().getTenhocsinh();
				lophs=diemlistByid.get(ii).getIDLopHS().getIdLopc().getTenlop();
				anh=diemlistByid.get(ii).getIDLopHS().getIdhs().getAnhHS();
			
				
			}
			System.out.println(anh+lophs);
			
			
		}
		
		model.addAttribute("anh", anh);
		model.addAttribute("tenhs", tenhs);
		model.addAttribute("hk", hk);
		model.addAttribute("lophs", lop);
		model.addAttribute("tkgv", tkgv);
		model.addAttribute("l", l);
		model.addAttribute("diemlistByid", diemlistByid);
		
		return "lop/showDetail";
	}
	
	@GetMapping("GV/GVCN/show/{lop}/{idM}/{idGVLM}/{idhs}")
	public String slddC2(ModelMap model, Authentication authentication, @PathVariable("lop") String lop,
			@PathVariable("idM") Integer idM, @PathVariable("idGVLM") String idGVLM,@PathVariable("idhs") String idhs) {
		
		TaiKhoanGv tkgv = TKGvRepo.findAllDetail(authentication.getName());
		
		System.out.println(idhs);
		List<GV_Lop_Mon> gvlmlist = gvlmrepo.findHS(idhs.trim());
		
		String idMon="";
        String hk="";
		for (int i = 0; i < gvlmlist.size(); i++) {
			idMon=gvlmlist.get(i).getIDMon().getIdMon().toString();
			hk= gvlmlist.get(i).getIDHocKy().getTenhocky();

		}
		List<Diem> diemlistByid = new ArrayList<>();

		Map<String, Double> l = new HashMap();
		
		Double tempHS1=0.0;
		Double tempHS2=0.0;
		Double tempHS3=0.0;
		Double tempTBM=0.0;
		
		String tenhs="";
		String lophs="";
		String anh= "";
		for (int i = 0; i < gvlmlist.size(); i++) {
			
			diemlistByid = DRepo.findDiemByIdHSIdMon(gvlmlist.get(i).getIDMon().getIdMon().toString().trim(), idhs);
			for (int j = 0; j < diemlistByid.size(); j++) {
				
				if(diemlistByid.get(j).getIDDauDiem().getLoaiDauDiem().equalsIgnoreCase("Miệng") ||diemlistByid.get(j).getIDDauDiem().getLoaiDauDiem().equalsIgnoreCase("15 phút")) {
					System.out.println(diemlistByid.get(j).getIDDauDiem().getLoaiDauDiem() +" = "+diemlistByid.get(j).getDiem());
					
					tempHS1= tempHS1 + diemlistByid.get(j).getDiem();
					System.out.println("Điểm hs 1: "+tempHS1);
				}
				
				if(diemlistByid.get(j).getIDDauDiem().getLoaiDauDiem().equalsIgnoreCase("1 Tiết")) {
					System.out.println(diemlistByid.get(j).getIDDauDiem().getLoaiDauDiem() +" = "+diemlistByid.get(j).getDiem());
					
					tempHS2= tempHS2 + diemlistByid.get(j).getDiem()*2;
					System.out.println("Điểm hs 2: "+tempHS2);
				}
				
				if(diemlistByid.get(j).getIDDauDiem().getLoaiDauDiem().equalsIgnoreCase("Học Kỳ")) {
					System.out.println(diemlistByid.get(j).getIDDauDiem().getLoaiDauDiem() +" = "+diemlistByid.get(j).getDiem());
					
					tempHS3= tempHS3 + diemlistByid.get(j).getDiem()*3;
					System.out.println("Điểm hs 3: "+tempHS3);
				}
			}
			tempTBM= (tempHS1+tempHS2+tempHS3)/(7+2+2+3);
			System.out.println("tb: "+tempTBM);
			
			tempHS3=0.0;
			tempHS2=0.0;
			tempHS1 = 0.0;
			//add vào Map: 
			for (int j = 0; j < diemlistByid.size(); j++) {
				l.put(gvlmlist.get(i).getIDMon().getTenMon().toString(), tempTBM);
                
			}
			tempTBM=0.0;
			
			for (Map.Entry<String,Double> entry : l.entrySet()) {
				System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue());
				model.addAttribute("l", l);
			}
       
			for (int ii = 0; ii < diemlistByid.size(); ii++) {
				
				tenhs=diemlistByid.get(ii).getIDLopHS().getIdhs().getTenhocsinh();
				lophs=diemlistByid.get(ii).getIDLopHS().getIdLopc().getTenlop();
				anh=diemlistByid.get(ii).getIDLopHS().getIdhs().getAnhHS();
			
				System.out.println(tenhs);
			}
			
		}
		
		

		
		model.addAttribute("hk", hk);
		model.addAttribute("anh", anh);
		model.addAttribute("lophs", lophs);
		model.addAttribute("tenhs", tenhs);
		model.addAttribute("tkgv", tkgv);
		model.addAttribute("l", l);
		model.addAttribute("diemlistByid", diemlistByid);
		return "GVCN/ShowDiem";
	}
}
