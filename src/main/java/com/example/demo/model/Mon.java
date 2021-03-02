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
@Table(name = "mon")
@Data
@Getter
@Setter
public class Mon {
	@Id
	private Integer IdMon;
	private String TenMon;
	
	@OneToMany(mappedBy = "IDMonc")
	private Collection<DauDiem> daudiem;
	
	public Collection<DauDiem> getDauDiem() {
		return daudiem;
	}
	 
	public void setDauDiem(Collection<DauDiem> daudiem) {
		this.daudiem = daudiem;
	}
	
}
