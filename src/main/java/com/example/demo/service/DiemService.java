package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Diem;
import com.example.demo.repositories.DiemRepository;


@Service
public class DiemService {
	
	@Autowired
	DiemRepository diemRepository;
	
	
	public List<Diem> getAllDiem(){
		return diemRepository.findAll();
	}
	
	public List<Diem> getAll(){
		return diemRepository.getAll();
	}
	public void save(Diem diem) {
		diemRepository.save(diem);
	}
	
}
