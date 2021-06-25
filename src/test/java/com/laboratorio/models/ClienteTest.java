package com.laboratorio.models;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class ClienteTest {


    private Long id = 1L;
    private String nombre = "christian";
    private String apellido = "rodriguez";
    private String email = "alexroguez59@gmail.com";
    private Date createAt = new Date();
    private List<Factura> facturas = new ArrayList<>();
    private String foto = "foto.jpg";


    @Test
    void crearObjetoConstrutorVacio() {
        Cliente cliente = new Cliente();

        cliente.setId(id);
        cliente.setNombre(nombre);
        cliente.setApellido(apellido);
        cliente.setEmail(email);
        cliente.setCreateAt(createAt);
        cliente.setFacturas(facturas);
        cliente.setFoto(foto);

        assertThat(cliente.getId()).isEqualTo(id);
        assertThat(cliente.getNombre()).isEqualTo(nombre);
        assertThat(cliente.getApellido()).isEqualTo(apellido);
        assertThat(cliente.getEmail()).isEqualTo(email);
        assertThat(cliente.getCreateAt()).isEqualTo(createAt);
        assertThat(cliente.getFacturas()).isEqualTo(facturas);
        assertThat(cliente.getFoto()).isEqualTo(foto);
    }

    @Test
    void crearObjeto() {
        Cliente cliente = Cliente.builder().build();
        assertNotNull(cliente);
    }
}