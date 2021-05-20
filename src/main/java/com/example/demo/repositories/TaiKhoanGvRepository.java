package com.example.demo.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.model.TaiKhoanGv;

@Repository
public interface TaiKhoanGvRepository extends JpaRepository<TaiKhoanGv, Integer> {

	@Query(value = "select * from taikhoangv where tentk = :tentk", nativeQuery = true)
	public Optional<TaiKhoanGv> findTaiKhoanGvByTentk(@Param("tentk") String username);
    
	@Query(value="select * from giaovien inner join taikhoangv on taikhoangv.idgv = giaovien.idgv where taikhoangv.tentk=:tentk", nativeQuery = true)
	public TaiKhoanGv findAllDetail(@Param("tentk") String username);
	
	
}
