package com.example.demo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "taikhoangv")
//@Data
//@Getter
//@Setter
public class TaiKhoanGv {
	@Id
	private Integer id;
	@Column(name = "tentk")
	private String tentk;
	@Column(name = "matkhau")
	private String matkhau;
	@Column(name = "chucvu")
	private String chucvu;
	
	@OneToOne
	@JoinColumn(name = "IDGV") 
	private GiaoVien giaovienfk;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTentk() {
		return tentk;
	}

	public void setTentk(String tentk) {
		this.tentk = tentk;
	}

	public String getMatkhau() {
		return matkhau;
	}

	public void setMatkhau(String matkhau) {
		this.matkhau = matkhau;
	}

	public String getChucvu() {
		return chucvu;
	}

	public void setChucvu(String chucvu) {
		this.chucvu = chucvu;
	}

	public GiaoVien getGiaovienfk() {
		return giaovienfk;
	}

	public void setGiaovienfk(GiaoVien giaovienfk) {
		this.giaovienfk = giaovienfk;
	} 
	
}
