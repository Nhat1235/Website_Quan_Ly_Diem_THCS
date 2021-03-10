package com.example.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.HocKy;

@Repository
public interface HocKyRepository extends JpaRepository<HocKy, Integer>{

}
