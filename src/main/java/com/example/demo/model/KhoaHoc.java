package com.example.demo.model;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="khoahoc")
public class KhoaHoc {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "IDKhoaHoc")
	private Integer IdKhoaHoc;
	
	private String Nam;
	
	  @OneToMany(mappedBy = "khoahocfk")
	    private Collection<Lop> lop;
		 
		public Collection<Lop> getLop() {
			return lop;
		}
		 
		public void setLop(Collection<Lop> lop) {
			this.lop = lop;
		}
		
		
		
		

		public KhoaHoc() {
		}

		
		
		public KhoaHoc(Integer idKhoaHoc, String nam, Collection<Lop> lop) {
			super();
			IdKhoaHoc = idKhoaHoc;
			Nam = nam;
			this.lop = lop;
		}

		public Integer getIdKhoaHoc() {
			return IdKhoaHoc;
		}

		public void setIdKhoaHoc(Integer idKhoaHoc) {
			IdKhoaHoc = idKhoaHoc;
		}

		public String getNam() {
			return Nam;
		}

		public void setNam(String nam) {
			Nam = nam;
		}
		
}
