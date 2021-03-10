package com.example.demo.model;

import javax.persistence.Column;
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
@Table(name = "lop_hs")
@Data
@Getter
@Setter
public class Lop_hs {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "IDLopHS")
   private Integer IdLopHs;
   
   @ManyToOne
   @JoinColumn(name="IDHocSinh")
   private HocSinh idhs;
   
   @ManyToOne
   @JoinColumn(name="IDLop")
   private Lop idLopc;
}
