package com.example.demo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Entity
@Table(name = "taikhoangv")

public class TaiKhoanGv {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Integer id;
	
	
	@Column(name = "tentk", unique = true)
	/*
	 * @NotNull(message="*Trường này không được phép trống")
	 * 
	 * @Size(min = 5, max = 15, message =
	 * "*Chỉ được nhập trong khoảng từ 5 đến 15 ký tự")
	 */
	private String tentk;
	
	/*
	 * @NotNull(message="*Trường này không được phép trống")
	 * 
	 * @Size(min = 8, max = 15, message =
	 * "*Chỉ được nhập trong khoảng từ 8 đến 15 ký tự")
	 */
	@Column(name = "matkhau")
	
	private String matkhau;
	/*
	 * @NotNull(message="*Trường này không được phép trống")
	 * 
	 * @Size( max = 4, message = "*Chỉ được nhập tối đa 4 ký tự")
	 */
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
