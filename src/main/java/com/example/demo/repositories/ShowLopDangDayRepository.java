package com.example.demo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.model.GV_Lop_Mon;

public interface ShowLopDangDayRepository extends JpaRepository<GV_Lop_Mon, Integer>{
	
	@Query(value = "select chucvu,tenMon, tenlop from gv_lop_mon inner join lop on gv_lop_mon.idlop = lop.idlop inner join giaovien on GV_lop_mon.idGV = giaovien.idGV inner join Mon on GV_lop_mon.idmon = mon.idmon",nativeQuery = true)
    List<GV_Lop_Mon> ShowLopDangDay();
    
    @Query(value = "select * from gv_lop_mon inner join lop on gv_lop_mon.idlop = lop.idlop inner join giaovien on GV_lop_mon.idGV = giaovien.idGV inner join Mon on GV_lop_mon.idmon = mon.idmon where ten=:ten",nativeQuery = true)
    List<GV_Lop_Mon> FindLopDangDay(@Param("ten")String TenGV);
    
    
}
