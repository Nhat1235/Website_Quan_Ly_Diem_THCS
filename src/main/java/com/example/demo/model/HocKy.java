package com.example.demo.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "hocky")
@Data
@Getter
@Setter
public class HocKy {
	@Id
	private Integer idhocki;
	private String tenhocki;
	private Integer nam;
}
