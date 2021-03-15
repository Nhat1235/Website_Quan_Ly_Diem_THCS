package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.DauDiem;
import com.example.demo.repositories.DauDiemRepository;

@Service
public class DauDiemService {

	@Autowired
	DauDiemRepository dauDiemRepository;
	
	public List<DauDiem> getAllDauDiem(){
		return dauDiemRepository.findAll();
	}
	
	public void save(DauDiem daudiem) {
		dauDiemRepository.save(daudiem);
	}
	
	public List<DauDiem> getDauDiemMon(){
		return dauDiemRepository.getDauDiemMon();
	}
	
	public void delete(Integer id) {
		dauDiemRepository.deleteById(id);
	}
	
}

