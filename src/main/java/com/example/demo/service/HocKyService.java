package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.HocKy;
import com.example.demo.model.HocSinh;
import com.example.demo.repositories.HocKyRepository;
import com.example.demo.repositories.HocSinhRepository;

@Service
public class HocKyService {
	
	@Autowired
	HocKyRepository hocKyRepository;
	
	public List<HocKy> getAllHocKy(){
		return hocKyRepository.findAll();
	}
	
	public void save(HocKy hocky) {
		hocKyRepository.save(hocky);
	}
	
}
