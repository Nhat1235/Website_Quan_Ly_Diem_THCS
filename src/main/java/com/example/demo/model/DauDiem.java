package com.example.demo.model;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "DauDiem")
@Data
@Getter
@Setter
public class DauDiem {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer idDauDiem;
	private String loaiDauDiem;

	@ManyToOne
	@JoinColumn(name="IDMon")
	private Mon IDMonc;
	
	
}
