package com.example.demo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.model.DauDiem;

@Repository
public interface DauDiemRepository extends JpaRepository<DauDiem, Integer>{

	@Query(value = "select * from daudiem inner join mon on mon.idmon = daudiem.idmon",nativeQuery = true)
	public List<DauDiem> getDauDiemMon();
	
}
