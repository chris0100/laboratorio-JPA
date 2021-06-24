package com.laboratorio.models.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
public class ClienteDTO implements Serializable {

	private Long id;
	private String nombre;
	private String apellido;
	private String email;
	private Date createAt;
	private List<FacturaDTO> facturas;
	private String foto;

	public ClienteDTO() {
		facturas = new ArrayList<>();
	}


	public static final long serialVersionUID = 1L;

}
