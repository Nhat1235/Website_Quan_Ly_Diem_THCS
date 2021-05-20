package com.example.demo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.model.GiaoVien;

@Repository

public interface GiaoVienRepository extends JpaRepository<GiaoVien, Integer>{

	@Query(value="select * from Giaovien where chucvu not like 'PDT' and chucvu not like 'BGH'", nativeQuery = true)
	List<GiaoVien> findGV();

	
	@Query(value="select * from Giaovien where ten=:tengv and chucvu=:chucvugv", nativeQuery = true) 
	List<GiaoVien> findID(@Param("tengv")String tengv, @Param("chucvugv")String chucvugv);
}
