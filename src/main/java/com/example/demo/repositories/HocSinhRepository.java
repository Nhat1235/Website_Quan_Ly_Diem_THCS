package com.example.demo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.model.GiaoVien;
import com.example.demo.model.HocSinh;

@Repository
public interface HocSinhRepository extends JpaRepository<HocSinh, Integer>{

	@Query(value="select * from hocsinh where tenhocsinh=:tengv", nativeQuery = true) 
	List<HocSinh> findID(@Param("tengv")String tengv);
}
