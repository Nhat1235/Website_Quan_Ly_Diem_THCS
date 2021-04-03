package com.example.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Mon;

@Repository
public interface MonRepository extends JpaRepository<Mon, Integer>{
    
	@Query(value = "select * from mon where idmon=:idM", nativeQuery = true)
	Mon findTenMonByIDMon(@Param("idM")Integer idM);

}
