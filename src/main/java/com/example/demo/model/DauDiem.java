package com.example.demo.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "DauDiem")

public class DauDiem {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "IDDauDiem")
	private Integer idDauDiem;
	private String loaiDauDiem;

	@ManyToOne
	@JoinColumn(name="IDMon")
	private Mon IDMonc;

	
	public DauDiem() {
	}

	public Integer getIdDauDiem() {
		return idDauDiem;
	}

	public void setIdDauDiem(Integer idDauDiem) {
		this.idDauDiem = idDauDiem;
	}

	public String getLoaiDauDiem() {
		return loaiDauDiem;
	}

	public void setLoaiDauDiem(String loaiDauDiem) {
		this.loaiDauDiem = loaiDauDiem;
	}

	public Mon getIDMonc() {
		return IDMonc;
	}

	public void setIDMonc(Mon iDMonc) {
		IDMonc = iDMonc;
	}
	
}
