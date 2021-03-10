package com.example.demo.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "hocky")

public class HocKy {
	@Id
	private Integer idhocki;
	private String tenhocki;
	private Integer nam;
	
	
	public Integer getIdhocki() {
		return idhocki;
	}
	public void setIdhocki(Integer idhocki) {
		this.idhocki = idhocki;
	}
	public String getTenhocki() {
		return tenhocki;
	}
	public void setTenhocki(String tenhocki) {
		this.tenhocki = tenhocki;
	}
	public Integer getNam() {
		return nam;
	}
	public void setNam(Integer nam) {
		this.nam = nam;
	}
	
}
