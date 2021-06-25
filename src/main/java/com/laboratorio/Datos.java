package com.laboratorio;

import com.laboratorio.models.Cliente;
import com.laboratorio.models.DTO.ClienteDTO;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class Datos {

    public final static List<Cliente> CLIENTES = Arrays.asList(Cliente.builder().id(1L).nombre("christian").apellido("rodriguez").createAt(new Date())
                    .email("alexroguez59@gmail.com").facturas(new ArrayList<>()).build(),
            Cliente.builder().id(2L).nombre("andrea").apellido("lozano").createAt(new Date())
                    .email("alexro@gmail.com").build(),
            Cliente.builder().id(3L).nombre("sandra").apellido("martinez").createAt(new Date())
                    .email("alexroguez@gmail.com").build());

    public final static List<ClienteDTO> CLIENTES_DTO = Arrays.asList(ClienteDTO.builder().id(1L).nombre("christian").apellido("rodriguez").build(),
            ClienteDTO.builder().id(2L).nombre("andrea").apellido("lozano").build(),
            ClienteDTO.builder().id(3L).nombre("sandra").apellido("martinez").build());
}
