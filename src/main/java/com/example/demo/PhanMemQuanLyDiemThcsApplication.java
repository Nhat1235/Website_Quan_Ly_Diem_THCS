package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.demo.model.TaiKhoanGv;
import com.example.demo.repositories.TaiKhoanGvRepository;

@SpringBootApplication
public class PhanMemQuanLyDiemThcsApplication {

	@Autowired
	public TaiKhoanGvRepository rep;
	
    
	public static void main(String[] args) {
		
		SpringApplication.run(PhanMemQuanLyDiemThcsApplication.class, args);
	    
	}

}
