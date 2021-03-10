package com.example.demo.model;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "mon")

public class Mon {
	@Id
	private Integer IdMon;
	private String TenMon;
	
	@OneToMany(mappedBy = "IDMonc")
	private Collection<DauDiem> daudiem;
	
	
	public Collection<DauDiem> getDauDiem() {
		return daudiem;
	}
	 
	public void setDauDiem(Collection<DauDiem> daudiem) {
		this.daudiem = daudiem;
	}

	public Integer getIdMon() {
		return IdMon;
	}

	public void setIdMon(Integer idMon) {
		IdMon = idMon;
	}

	public String getTenMon() {
		return TenMon;
	}

	public void setTenMon(String tenMon) {
		TenMon = tenMon;
	}

	public Collection<DauDiem> getDaudiem() {
		return daudiem;
	}

	public void setDaudiem(Collection<DauDiem> daudiem) {
		this.daudiem = daudiem;
	}
	
	
}
