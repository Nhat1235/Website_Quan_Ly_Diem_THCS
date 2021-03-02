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
@Table(name="khoahoc")
@Data
@Getter
@Setter
public class KhoaHoc {
	@Id
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
}
