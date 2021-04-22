package com.example.demo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.model.GV_Lop_Mon;

@Repository
public interface GV_L_MRepository extends JpaRepository<GV_Lop_Mon, Integer>{
	
	@Query(value="select * from GV_Lop_Mon inner join HocKy on HocKy.IDHocKy = GV_Lop_Mon.IDHocKy\r\n" + 
			"						inner join Lop on Lop.IDLop = GV_Lop_Mon.IDLop\r\n" + 
			"                        inner join Mon on Mon.IDMon = GV_Lop_Mon.IDMon\r\n" + 
			"                        inner join GiaoVien on GiaoVien.IDGV = GV_Lop_Mon.IDGV", nativeQuery = true)
    List<GV_Lop_Mon> getAll();
	
    @Query(value="select distinct * from gv_lop_mon inner join mon on mon.idmon=gv_lop_mon.idmon inner join lop on gv_lop_mon.idlop = lop.idlop inner join lop_hs on lop_hs.idlop = lop.idlop inner join hocsinh on hocsinh.idhocsinh = lop_hs.idhocsinh where hocsinh.idhocsinh=1",nativeQuery = true)
	List<GV_Lop_Mon>findHS(@Param("idhs")String idhs);
}
