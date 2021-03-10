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
@Table(name = "lop")

public class Lop {
	@Id
	private Integer idlop;
	private String tenlop;
    
	
	@ManyToOne
	@JoinColumn(name="IdKhoaHoc")
	private KhoaHoc khoahocfk;
	
    @OneToMany(mappedBy = "idLopc")
    private Collection<Lop_hs> lopc;
	 
	public Collection<Lop_hs> getLop_hs() {
		return lopc;
	}
	 
	public void setLop_hs(Collection<Lop_hs> lopc) {
		this.lopc = lopc;
	}

	public Integer getIdlop() {
		return idlop;
	}

	public void setIdlop(Integer idlop) {
		this.idlop = idlop;
	}

	public String getTenlop() {
		return tenlop;
	}

	public void setTenlop(String tenlop) {
		this.tenlop = tenlop;
	}

	public KhoaHoc getKhoahocfk() {
		return khoahocfk;
	}

	public void setKhoahocfk(KhoaHoc khoahocfk) {
		this.khoahocfk = khoahocfk;
	}

	public Collection<Lop_hs> getLopc() {
		return lopc;
	}

	public void setLopc(Collection<Lop_hs> lopc) {
		this.lopc = lopc;
	}
	
}
