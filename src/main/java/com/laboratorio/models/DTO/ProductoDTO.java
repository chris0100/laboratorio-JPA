package com.laboratorio.models.DTO;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class ProductoDTO implements Serializable {


	private Long id;
	private String nombre;
	private Double precio;
	private Date createAt;

	public static final long serialVersionUID = 1L;

}
