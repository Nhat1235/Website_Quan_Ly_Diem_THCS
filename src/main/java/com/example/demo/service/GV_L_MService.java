package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.GV_Lop_Mon;
import com.example.demo.repositories.GV_L_MRepository;

@Service
public class GV_L_MService {

	
	@Autowired
	GV_L_MRepository gv_L_MRepository;
	
	public List<GV_Lop_Mon> getAll(){
		return gv_L_MRepository.getAll();
	}
	
	public void save(GV_Lop_Mon gv_Lop_Mon) {
		gv_L_MRepository.save(gv_Lop_Mon);
	}
	
}
