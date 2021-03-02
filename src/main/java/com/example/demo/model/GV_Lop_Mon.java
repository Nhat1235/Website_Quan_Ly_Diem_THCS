package com.example.demo.model;

import java.util.Collection;

import javax.persistence.Entity;
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
@Data
@Getter
@Setter
public class GV_Lop_Mon {
	@Id
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
}
