package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.GiaoVien;
import com.example.demo.model.HocKy;
import com.example.demo.repositories.GiaoVienRepository;

@Service
public class GiaoVienService {
	
	@Autowired
	GiaoVienRepository giaoVienRepository;
	
	public List<GiaoVien> getAllGV(){
		return giaoVienRepository.findAll();
	}
	
	public void save(GiaoVien giaovien) {
		giaoVienRepository.save(giaovien);
	}
	
}
