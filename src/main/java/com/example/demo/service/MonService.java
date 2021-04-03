package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Mon;
import com.example.demo.repositories.MonRepository;

@Service
public class MonService {
	
	@Autowired
	MonRepository monRepository;
	
	public List<Mon> getAllMon(){
		return monRepository.findAll();
	}
	
	public void save(Mon mon) {
		monRepository.save(mon);
	}
	
	public void delete(Integer id) {
		monRepository.deleteById(id);
	}
}
