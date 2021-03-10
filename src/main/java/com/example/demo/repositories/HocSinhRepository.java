package com.example.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.HocSinh;

@Repository
public interface HocSinhRepository extends JpaRepository<HocSinh, Integer>{

}
