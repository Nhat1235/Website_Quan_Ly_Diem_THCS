package com.example.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Diem;

@Repository
public interface DiemRepository extends JpaRepository<Diem, Integer>{

}
