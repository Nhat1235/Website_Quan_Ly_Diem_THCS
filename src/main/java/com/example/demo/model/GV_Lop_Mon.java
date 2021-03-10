package com.example.demo.model;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "gv_lop_mon")
public class GV_Lop_Mon {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "IDGV_L_M")
	private Integer IdGv_L_M;
	
	private boolean TrangThai;
	
	@ManyToOne
	@JoinColumn(name = "IDHocKy")
	private HocKy IDHocKy;
	
	@ManyToOne
	@JoinColumn(name = "IDLop")
	private Lop IDLop;
	
	@ManyToOne
	@JoinColumn(name = "IDGV")
	private GiaoVien IDGV;
	
	@ManyToOne
	@JoinColumn(name = "IDMon")
	private Mon IDMon;
	
	@OneToMany(mappedBy = "Diem")
	private Collection<Diem> diem;
	
	public Collection<Diem> getDiem() {
		return diem;
	}
	 
	public void setDiem(Collection<Diem> Diem) {
		this.diem = Diem;
	}

	public Integer getIdGv_L_M() {
		return IdGv_L_M;
	}

	public void setIdGv_L_M(Integer idGv_L_M) {
		IdGv_L_M = idGv_L_M;
	}

	public boolean isTrangThai() {
		return TrangThai;
	}

	public void setTrangThai(boolean trangThai) {
		TrangThai = trangThai;
	}

	public HocKy getIDHocKy() {
		return IDHocKy;
	}

	public void setIDHocKy(HocKy iDHocKy) {
		IDHocKy = iDHocKy;
	}

	public Lop getIDLop() {
		return IDLop;
	}

	public void setIDLop(Lop iDLop) {
		IDLop = iDLop;
	}

	public GiaoVien getIDGV() {
		return IDGV;
	}

	public void setIDGV(GiaoVien iDGV) {
		IDGV = iDGV;
	}

	public Mon getIDMon() {
		return IDMon;
	}

	public void setIDMon(Mon iDMon) {
		IDMon = iDMon;
	}
	
	
}
