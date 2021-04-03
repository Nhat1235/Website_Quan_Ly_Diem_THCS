package com.example.demo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.model.KhoaHoc;

@Repository
public interface KhoaHocRepository extends JpaRepository<KhoaHoc, Integer>{
	
	public List<KhoaHoc> findAll();

	@Query(value = "select * from khoahoc where idkhoahoc=:idkhoahoc",nativeQuery = true)
	public List<KhoaHoc> findAll(@Param("idkhoahoc")String keyword);
	
//	@Query(value = "select distinct nam from khoahoc",nativeQuery = true)
//	public List<KhoaHoc> getNamKhoaHoc();
}
