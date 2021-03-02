package com.example.demo.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "diem")
@Data
@Getter
@Setter
public class Diem {
	@Id
	private Integer idDiem;
	private Integer Diem;
	private boolean TrangThai;
    
	@ManyToOne
	@JoinColumn(name="IDGV_L_M")
	private GV_Lop_Mon IDGV_L_M;
	
	@ManyToOne
	@JoinColumn(name="IDDauDiem")
	private Diem IDDauDiem;
	
	@ManyToOne
	@JoinColumn(name="IDLopHS")
	private Lop_hs IDLopHS;
	
}
