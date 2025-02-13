package com.example.demo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.model.Diem;
import com.example.demo.model.Lop_hs;

public interface HocSinh_LopRepository extends JpaRepository<Lop_hs, Integer> {
 
	@Query(value="select distinct * from  lop_hs inner join lop on lop_hs.IDLop = lop.IDLop inner join hocsinh on lop_hs.IDHocSinh = hocsinh.IDHocSinh where lop.tenlop=:lop", nativeQuery = true)
    List<Lop_hs> getHS(@Param("lop")String lop);
    
    
    @Query(value="select * from  lop_hs inner join lop on lop_hs.IDLop = lop.IDLop inner join hocsinh on lop_hs.IDHocSinh = hocsinh.IDHocSinh where lop.IDlop=:lop", nativeQuery = true)
	List<Lop_hs> findByIDlop(@Param("lop")String lop);

}
