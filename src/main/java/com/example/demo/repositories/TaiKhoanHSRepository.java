package com.example.demo.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.model.TaiKhoanHs;

@Repository

public interface TaiKhoanHSRepository extends JpaRepository<TaiKhoanHs, Integer> {

	@Query(value = "select * from taikhoahs where tentk = :tentk", nativeQuery = true)
	public Optional<TaiKhoanHs> findTaiKhoanHsByTentk(@Param("tentk") String username);
	
	@Query(value="select * from hocsinh inner join taikhoanhs on taikhoanhs.IDHocSinh = hocsinh.IDHocSinh where taikhoanhs.tentk=:tentk", nativeQuery = true)
	public TaiKhoanHs findAllDetail(@Param("tentk") String username);
	
}
