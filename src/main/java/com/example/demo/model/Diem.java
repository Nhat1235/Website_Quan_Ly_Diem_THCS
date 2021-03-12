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
	private DauDiem IDDauDiem;
	
	@ManyToOne
	@JoinColumn(name="IDLopHS")
	private Lop_hs IDLopHS;

	public Integer getIdDiem() {
		return idDiem;
	}

	public void setIdDiem(Integer idDiem) {
		this.idDiem = idDiem;
	}

	public Integer getDiem() {
		return Diem;
	}

	public void setDiem(Integer diem) {
		Diem = diem;
	}

	public boolean isTrangThai() {
		return TrangThai;
	}

	public void setTrangThai(boolean trangThai) {
		TrangThai = trangThai;
	}

	public GV_Lop_Mon getIDGV_L_M() {
		return IDGV_L_M;
	}

	public void setIDGV_L_M(GV_Lop_Mon iDGV_L_M) {
		IDGV_L_M = iDGV_L_M;
	}

	public DauDiem getIDDauDiem() {
		return IDDauDiem;
	}

	public void setIDDauDiem(DauDiem iDDauDiem) {
		IDDauDiem = iDDauDiem;
	}

	public Lop_hs getIDLopHS() {
		return IDLopHS;
	}

	public void setIDLopHS(Lop_hs iDLopHS) {
		IDLopHS = iDLopHS;
	}
	
	
}
