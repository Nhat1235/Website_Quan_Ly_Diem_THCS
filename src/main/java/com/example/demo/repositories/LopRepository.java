package com.example.demo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Lop;

@Repository
public interface LopRepository extends JpaRepository<Lop, Integer>{
	
	
	@Query(value = "select * from lop inner join khoahoc on lop.idkhoahoc = khoahoc.idkhoahoc where TenLop not like 'Chưa có lớp';",nativeQuery = true)
	public List<Lop> getLopKhoaHoc();
	
	@Query(value = "select * from lop inner join khoahoc on lop.idkhoahoc = khoahoc.idkhoahoc;",nativeQuery = true)
	public List<Lop> getLopKhoaHoc2();
	
	@Query(value = "select * from lop where tenlop=:ten",nativeQuery = true)
	public List<Lop> getLop(@Param("ten")String ten);

	/* public Lop findByTenLop(String string); */
	
	
	
}
