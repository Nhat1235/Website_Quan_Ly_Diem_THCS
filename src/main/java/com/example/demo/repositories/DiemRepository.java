package com.example.demo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Diem;

@Repository
public interface DiemRepository extends JpaRepository<Diem, Integer>{
	 @Query(value="select * from diem \r\n" + 
	    		"inner join lop_hs inner join lop on lop_hs.idlop = lop.idlop inner join hocsinh on lop_hs.idhocsinh = hocsinh.idhocsinh on diem.IdlopHS = lop_hs.idlophs \r\n" + 
	    		"inner join daudiem on diem.iddaudiem = daudiem.iddaudiem \r\n" + 
	    		"inner join GV_lop_mon inner join giaovien on GV_lop_mon.idGV = giaovien.idGV inner join Mon on GV_lop_mon.idmon = mon.idmon on diem.idgv_l_m = GV_lop_mon.idgv_l_m\r\n" + 
	    		"where tenlop=:tenlop",nativeQuery = true)
	 List<Diem>findDiemLop(@Param("tenlop")String tenlop);
	 
	 
	 
//	 List<Diem>findDiemByDauDiem(@Param("daudiem")String daudiem);
	 
}
