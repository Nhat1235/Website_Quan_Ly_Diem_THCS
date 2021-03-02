package com.example.demo.model;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "hocsinh")
@Data
@Getter
@Setter
public class HocSinh {
	@Id
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
}
