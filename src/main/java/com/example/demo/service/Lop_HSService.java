package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Lop_hs;
import com.example.demo.repositories.Lop_HocSinhrepository;

@Service
public class Lop_HSService {

	@Autowired
	Lop_HocSinhrepository lop_HocSinhrepository;

	
	public List<Lop_hs> getAllLop_HS(){
		return lop_HocSinhrepository.findAll();
		
	}
	
	public void save(Lop_hs lop_hs) {
		lop_HocSinhrepository.save(lop_hs);
	}
	
	public void delete(Integer id) {
		lop_HocSinhrepository.deleteById(id);
	}


	public List<Lop_hs> getLopHS(String lop) {
		// TODO Auto-generated method stub
		return lop_HocSinhrepository.getLopHS(lop);
	}
	
	public List<Lop_hs> getLopHS() {
		// TODO Auto-generated method stub
		return lop_HocSinhrepository.getLopHS();
	}
	
	
	
}
