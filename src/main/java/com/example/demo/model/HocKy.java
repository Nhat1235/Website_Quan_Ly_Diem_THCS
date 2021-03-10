package com.example.demo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "hocky")

public class HocKy {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "IDHocKy")
	private Integer idhocky;
	
	private String tenhocky;
	
	private Integer nam;
	
	public HocKy() {
		
	}
	public HocKy(Integer idhocky, String tenhocky, Integer nam) {
		super();
		this.idhocky = idhocky;
		this.tenhocky = tenhocky;
		this.nam = nam;
	}
	public Integer getIdhocky() {
		return idhocky;
	}
	public void setIdhocky(Integer idhocky) {
		this.idhocky = idhocky;
	}
	public String getTenhocky() {
		return tenhocky;
	}
	public void setTenhocky(String tenhocky) {
		this.tenhocky = tenhocky;
	}
	public Integer getNam() {
		return nam;
	}
	public void setNam(Integer nam) {
		this.nam = nam;
	}
	
}
