package com.example.demo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "taikhoanhs")
public class TaiKhoanHs {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Integer id;
	
	private String tentk;
	
	private String matkhau;
	
	private String chucvu;


	@OneToOne
	@JoinColumn(name = "IDHocSinh") 
	private HocSinh hocsinhfk;


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


	public HocSinh getHocsinhfk() {
		return hocsinhfk;
	}


	public void setHocsinhfk(HocSinh hocsinhfk) {
		this.hocsinhfk = hocsinhfk;
	} 
	
	
	
}
