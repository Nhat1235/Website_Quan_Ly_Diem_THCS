package com.example.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.GiaoVien;

@Repository
public interface GiaoVienRepository extends JpaRepository<GiaoVien, Integer> {

}
