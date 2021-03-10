package com.example.demo.model;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "hocsinh")
public class HocSinh {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "IDHocSinh")
	private Integer idHocsinh;
	
	private String tenhocsinh;
	
	@OneToMany(mappedBy = "idhs")
	private Collection<Lop_hs> lop_hs;
	 
	public Collection<Lop_hs> getLop_hs() {
		return lop_hs;
	}
	 
	public void setLop_hs(Collection<Lop_hs> Lop_hs) {
		this.lop_hs = Lop_hs;
	}

	
	
	
	
	public HocSinh() {
	}

	public HocSinh(Integer idHocsinh, String tenhocsinh, Collection<Lop_hs> lop_hs) {
		super();
		this.idHocsinh = idHocsinh;
		this.tenhocsinh = tenhocsinh;
		this.lop_hs = lop_hs;
	}

	public Integer getIdHocsinh() {
		return idHocsinh;
	}

	public void setIdHocsinh(Integer idHocsinh) {
		this.idHocsinh = idHocsinh;
	}

	public String getTenhocsinh() {
		return tenhocsinh;
	}

	public void setTenhocsinh(String tenhocsinh) {
		this.tenhocsinh = tenhocsinh;
	}
	
	
	
	
}
