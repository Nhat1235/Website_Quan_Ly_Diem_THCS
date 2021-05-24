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
	
	
	 @Query(value="select distinct * from gv_lop_mon inner join mon on mon.idmon=gv_lop_mon.idmon inner join lop on gv_lop_mon.idlop = lop.idlop inner join lop_hs on lop_hs.idlop = lop.idlop inner join hocsinh on hocsinh.idhocsinh = lop_hs.idhocsinh where lop.idlop=:idlop",nativeQuery = true)
     List<GV_Lop_Mon>findLop(@Param("idlop")Integer idlop);
     
     @Query(value="select * from gv_lop_mon inner join lop on gv_lop_mon.idlop = lop.idlop inner join khoahoc on lop.idkhoahoc= khoahoc.idkhoahoc inner join giaovien on GV_lop_mon.idGV = giaovien.idGV inner join Mon on GV_lop_mon.idmon = mon.idmon where tenlop=:tenlop and Nam=:Nam",nativeQuery = true)
     List<GV_Lop_Mon>findLopDetails(@Param("tenlop")String tenlop, @Param("Nam")String nam);
     
     @Query(value="select * from gv_lop_mon inner join lop on gv_lop_mon.idlop = lop.idlop inner join khoahoc on lop.idkhoahoc= khoahoc.idkhoahoc inner join giaovien on GV_lop_mon.idGV = giaovien.idGV inner join Mon on GV_lop_mon.idmon = mon.idmon where tenlop=:tenlop and khoahoc.idkhoahoc=:Nam and TenMon=:tenmon",nativeQuery = true)
     List<GV_Lop_Mon>countLopDetails(@Param("tenlop")String tenlop, @Param("Nam")String nam, @Param("tenmon")String tenmon);
     
     
     @Query(value="select * from Gv_lop_mon where idlop=:idlop",nativeQuery = true)
     List<GV_Lop_Mon>getlopbyidl(@Param("idlop")String idlop);
     
     @Query(value="select * from Gv_lop_mon where idgv_l_m=:idlop",nativeQuery = true)
     public GV_Lop_Mon getgvlm(@Param("idlop")String idlop);
}
