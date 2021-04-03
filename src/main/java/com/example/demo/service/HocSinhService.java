package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.HocSinh;
import com.example.demo.repositories.DiemRepository;
import com.example.demo.repositories.HocSinhRepository;

@Service
public class HocSinhService {
	
	
	@Autowired 
	HocSinhRepository hocSinhRepository;
	
	public void save(HocSinh hocsinh) {
		hocSinhRepository.save(hocsinh);
	}
	
	public void delete(Integer id) {
		hocSinhRepository.deleteById(id);	
	}
	
}
