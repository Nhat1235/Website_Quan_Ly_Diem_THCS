package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.HocSinh;
import com.example.demo.model.KhoaHoc;
import com.example.demo.repositories.KhoaHocRepository;

@Service
public class KhoaHocService {

	@Autowired
	KhoaHocRepository khoaHocRepository;
	
	public List<KhoaHoc> getAllKhoaHoc() {
		return khoaHocRepository.findAll();
	}
	
	public void save(KhoaHoc khoahoc) {
		khoaHocRepository.save(khoahoc);
	}
//	public List<KhoaHoc> getNamKhoaHoc() {
//		return khoaHocRepository.getNamKhoaHoc();
//	}
}
