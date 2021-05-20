package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.model.Lop;
import com.example.demo.repositories.LopRepository;

@Service
public class LopService {

	@Autowired
	LopRepository lopRepository;

	public List<Lop> getAllLop() {
		return lopRepository.findAll();
	}

	public void save(Lop lop) {
		lopRepository.save(lop);
		
	}

	public List<Lop> getLopKhoaHoc() {
		return lopRepository.getLopKhoaHoc();
	}
}
