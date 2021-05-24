package com.example.demo.repositories;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
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

	 @Query(value="select * from diem \r\n" + 
	    		"inner join lop_hs inner join lop on lop_hs.idlop = lop.idlop inner join hocsinh on lop_hs.idhocsinh = hocsinh.idhocsinh on diem.IdlopHS = lop_hs.idlophs \r\n" + 
	    		"inner join daudiem on diem.iddaudiem = daudiem.iddaudiem \r\n" + 
	    		"inner join GV_lop_mon inner join giaovien on GV_lop_mon.idGV = giaovien.idGV inner join Mon on GV_lop_mon.idmon = mon.idmon on diem.idgv_l_m = GV_lop_mon.idgv_l_m\r\n" + 
	    		"where tenlop=:tenlop and idGV_L_M=:idglvm",nativeQuery = true)
	 List<Diem>findDiemLop(@Param("tenlop")String tenlop, @Param("idglvm")String idglvm );
	 
	 @Query(value="select * from lop_hs inner join diem on lop_hs.idlophs = diem.idlophs where idlop=:lop and idhocsinh=:idlhs and idgv_l_m=:idgvlm",nativeQuery = true)
	 List<Diem>findDiemById(@Param("lop")String lop,@Param("idlhs")String idlhs,@Param("idgvlm")String idgvlm);
	 
	 @Query(value="select * from diem where idlophs=:idlhs",nativeQuery = true)
	 List<Diem>findDiemById(@Param("idlhs")String idlhs);
	 
	 @Query(value="select * from diem where idlophs=:idlhs and idgv_l_m=:idgvlm",nativeQuery = true)
	 List<Diem>findDiemById(@Param("idlhs")String idlhs ,@Param("idgvlm")String idgvlm);
	 
	 @Query(value="select * from diem inner join lop_hs inner join lop on lop_hs.idlop = lop.idlop inner join hocsinh on lop_hs.idhocsinh = hocsinh.idhocsinh on diem.IdlopHS = lop_hs.idlophs \r\n" + 
	 		"inner join daudiem on diem.iddaudiem = daudiem.iddaudiem \r\n" + 
	 		"inner join GV_lop_mon inner join giaovien on GV_lop_mon.idGV = giaovien.idGV inner join Mon on GV_lop_mon.idmon = mon.idmon on diem.idgv_l_m = GV_lop_mon.idgv_l_m where GV_lop_mon.idmon=:idmon and lop_hs.idhocsinh=:idhs" 
,nativeQuery = true)
	 List<Diem>findDiemByIdHSIdMon(@Param("idmon")String idmon,@Param("idhs")String idhs);
	 
	 @Query(value="select * from Diem inner join Lop_HS on Diem.IDLopHS = Lop_HS.IDLopHS\r\n" + 
	 		"					inner join DauDiem on Diem.IDDauDiem = DauDiem.IDDauDiem\r\n" + 
	 		"                    inner join GV_Lop_Mon on Diem.IDGV_L_M = GV_Lop_Mon.IDGV_L_M",nativeQuery = true)
	 List<Diem> getAll();
	 
	 
	 @Query(value="select * from Diem inner join Lop_HS on Diem.IDLopHS = Lop_HS.IDLopHS\r\n" + 
		 		"					inner join DauDiem on Diem.IDDauDiem = DauDiem.IDDauDiem\r\n" + 
		 		"                    inner join GV_Lop_Mon on Diem.IDGV_L_M = GV_Lop_Mon.IDGV_L_M where Lop_HS.IDLopHS=:lophs and Diem.IDDauDiem=:iddaudiem",nativeQuery = true)
	 List<Diem> HelpMe(@Param("lophs")String lophs,@Param("iddaudiem")String iddaudiem);
	 
	 @Modifying
	 @Transactional
	 @Query(value="delete from diem where idlophs =:lophs and IDDauDiem >=:ddiem",nativeQuery = true)
	 void deleteAllByLopHS(@Param("lophs")String lophs, @Param("ddiem")String ddiem);
	 
	 @Modifying
	 @Query(value="INSERT INTO diem (diem,trangthai,IDLopHS,IDDauDiem,IDGV_L_M) values(:diemso,0,:lophs,:ddiem,:idglm);",nativeQuery = true)
	 @org.springframework.transaction.annotation.Transactional
	
	 void insertDiemIntoExcel(@Param("diemso")Double diemso,@Param("lophs")String lophs, @Param("ddiem")String ddiem,  @Param("idglm")Integer idglm);
}
