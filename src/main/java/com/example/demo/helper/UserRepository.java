package com.example.demo.helper;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Diem;


 
public interface UserRepository extends JpaRepository<Diem, Integer> {
     
}
 
 
