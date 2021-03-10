package com.example.demo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "giaovien")

public class GiaoVien {
	@Id
	@GeneratedValue
	private Integer IdGv;

	@Column(name = "Ten")
	private String ten;
	@Column(name = "ChucVu")
	private String chucvu;

	public Integer getIdGv() {
		return IdGv;
	}

	public void setIdGv(Integer idGv) {
		IdGv = idGv;
	}

	public String getTen() {
		return ten;
	}

	public void setTen(String ten) {
		this.ten = ten;
	}

	public String getChucvu() {
		return chucvu;
	}

	public void setChucvu(String chucvu) {
		this.chucvu = chucvu;
	}

}
