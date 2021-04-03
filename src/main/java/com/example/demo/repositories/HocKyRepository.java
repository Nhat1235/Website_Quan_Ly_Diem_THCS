package com.example.demo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.model.HocKy;

@Repository
public interface HocKyRepository extends JpaRepository<HocKy, Integer>{

	@Query(value="select * from hocky;",nativeQuery = true)
	List<HocKy> findAllHK();
	
	@Query(value="select * from hocky where IDHocKy=1;",nativeQuery = true)
	List<HocKy> findAllHKbyID();
}
