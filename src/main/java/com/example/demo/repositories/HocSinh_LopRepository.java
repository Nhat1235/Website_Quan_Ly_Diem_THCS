package com.example.demo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.model.Lop_hs;

public interface HocSinh_LopRepository extends JpaRepository<Lop_hs, Integer> {
 
	@Query(value="select * from  lop_hs inner join lop on lop_hs.IDLop = lop.IDLop inner join hocsinh on lop_hs.IDHocSinh = hocsinh.IDHocSinh", nativeQuery = true)
    List<Lop_hs> getHS();
}
