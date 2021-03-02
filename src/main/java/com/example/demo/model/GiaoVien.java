package com.example.demo.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="giaovien")
@Data
@Getter
@Setter
public class GiaoVien {
	@Id
   private Integer IdGv;
   private String ten;
   private String chucvu;
   
   
   
}
