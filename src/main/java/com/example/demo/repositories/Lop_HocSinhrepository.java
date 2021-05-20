package com.example.demo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.model.Lop_hs;

@Repository
public interface Lop_HocSinhrepository extends JpaRepository<Lop_hs, Integer> {
	
	@Query(value = "\r\n" + 
			"select * from Lop_HS inner join Lop on Lop_HS.IDLop = Lop.IDLop\r\n" + 
			"					 inner join HocSinh on Lop_HS.IDHocSinh = HocSinh.IDHocSinh where lop.tenlop=:lop",nativeQuery = true)
	public List<Lop_hs> getLopHS(@Param("lop")String lop);
	
	@Query(value = "\r\n" + 
			"select * from Lop_HS inner join Lop on Lop_HS.IDLop = Lop.IDLop\r\n" + 
			"					 inner join HocSinh on Lop_HS.IDHocSinh = HocSinh.IDHocSinh where lop.idlop=:idlop",nativeQuery = true)
	public List<Lop_hs> getLopHSbyid(@Param("idlop")String idlop);
	
	
	@Query(value = "\r\n" + 
			"select * from Lop_HS inner join Lop on Lop_HS.IDLop = Lop.IDLop\r\n" + 
			"					 inner join HocSinh on Lop_HS.IDHocSinh = HocSinh.IDHocSinh",nativeQuery = true)
	public List<Lop_hs> getLopHS();

	@Query(value = "select * from Lop_HS inner join Lop on Lop_HS.IDLop = Lop.IDLop inner join HocSinh on Lop_HS.IDHocSinh = HocSinh.IDHocSinh where lop.idlop=:idlop ",nativeQuery = true)
	public List<Lop_hs> findByIdLop(@Param("idlop")Integer idlop);
	
    @Modifying
    @Transactional
	@Query(value= "update lop_hs set IDlop = 4 where idlophs=:idlophs", nativeQuery = true)
	public void updateLHS(@Param("idlophs")Integer idlophs);
    
    
    @Modifying
    @Transactional
	@Query(value= "update lop_hs set IDlop=:idlop where idlophs=:idlophs", nativeQuery = true)
	public void updateLHS2(@Param("idlophs")Integer idlophs,@Param("idlop")Integer idlop);

    
    @Query(value= "select * from lop_hs where idlop=4", nativeQuery = true)
	public List<Lop_hs> findHSWithoutLop();
	
}
