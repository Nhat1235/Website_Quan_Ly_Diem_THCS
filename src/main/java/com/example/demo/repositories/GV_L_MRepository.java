package com.example.demo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.model.GV_Lop_Mon;

@Repository
public interface GV_L_MRepository extends JpaRepository<GV_Lop_Mon, Integer>{
	
	@Query(value="select * from GV_Lop_Mon inner join HocKy on HocKy.IDHocKy = GV_Lop_Mon.IDHocKy\r\n" + 
			"						inner join Lop on Lop.IDLop = GV_Lop_Mon.IDLop\r\n" + 
			"                        inner join Mon on Mon.IDMon = GV_Lop_Mon.IDMon\r\n" + 
			"                        inner join GiaoVien on GiaoVien.IDGV = GV_Lop_Mon.IDGV", nativeQuery = true)
    List<GV_Lop_Mon> getAll();
	
	
}
