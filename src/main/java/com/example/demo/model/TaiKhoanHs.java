package com.example.demo.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "taikhoanhs")
@Data
@Getter
@Setter
public class TaiKhoanHs {
	@Id
	private Integer id;
	private String tentk;
	private String matkhau;
	private String chucvu;
	
	@OneToOne
	@JoinColumn(name = "IDHocSinh") 
	private HocSinh hocsinhfk; 
}
