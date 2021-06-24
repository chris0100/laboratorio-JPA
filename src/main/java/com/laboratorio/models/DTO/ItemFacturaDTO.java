package com.laboratorio.models.DTO;


import lombok.Data;

import java.io.Serializable;

@Data
public class ItemFacturaDTO implements Serializable {

	private Long id;
	private Integer cantidad;
	private ProductoDTO producto;


	public static final long serialVersionUID = 1L;
}