package com.example.demo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Lop_hs;

@Repository
public interface Lop_HocSinhrepository extends JpaRepository<Lop_hs, Integer> {
	
	@Query(value = "\r\n" + 
			"select * from Lop_HS inner join Lop on Lop_HS.IDLop = Lop.IDLop\r\n" + 
			"					 inner join HocSinh on Lop_HS.IDHocSinh = HocSinh.IDHocSinh where lop.tenlop=:lop",nativeQuery = true)
	public List<Lop_hs> getLopHS(@Param("lop")String lop);
	
	@Query(value = "\r\n" + 
			"select * from Lop_HS inner join Lop on Lop_HS.IDLop = Lop.IDLop\r\n" + 
			"					 inner join HocSinh on Lop_HS.IDHocSinh = HocSinh.IDHocSinh where lop.tenlop=:lop",nativeQuery = true)
	public List<Lop_hs> getLopHS();
}
