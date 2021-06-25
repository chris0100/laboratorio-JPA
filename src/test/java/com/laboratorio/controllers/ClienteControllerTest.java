package com.laboratorio.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.laboratorio.Datos;
import com.laboratorio.models.Cliente;
import com.laboratorio.models.DTO.ClienteDTO;
import com.laboratorio.services.ClienteService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.dao.DataAccessException;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ClienteController.class)
@EnableWebMvc
class ClienteControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private ClienteService clienteService;

    @MockBean
    ModelMapper modelMapper;

    ObjectMapper objectMapper;

    @BeforeEach
    void setUp(){
        objectMapper = new ObjectMapper();
    }


    @Test
    void testGetCliente() throws Exception {
        Cliente cliente = Datos.CLIENTES.get(0);
        ClienteDTO clienteDTO = Datos.CLIENTES_DTO.get(0);


        when(clienteService.findClienteById(anyLong())).thenReturn(cliente);
        when(modelMapper.map(cliente, ClienteDTO.class)).thenReturn(clienteDTO);

        mvc.perform(get("/api/cliente/ver/1").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.nombre").value("christian"))
                .andExpect(jsonPath("$.apellido").value("rodriguez"))
                .andExpect(content().json(objectMapper.writeValueAsString(clienteDTO)));

        verify(clienteService).findClienteById(1L);
    }


    @Test
    void testGetClienteError() throws Exception {

        when(clienteService.findClienteById(anyLong())).thenThrow(new DataAccessException("error") {
            @Override
            public String getMessage() {
                return super.getMessage();
            }
        });


        // When
        mvc.perform(get("/api/cliente/ver/1").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isInternalServerError())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));

        verify(clienteService).findClienteById(1L);

    }



    @Test
    void testGetClienteNull() throws Exception {

        when(clienteService.findClienteById(anyLong())).thenReturn(null);


        // When
        mvc.perform(get("/api/cliente/ver/1"))
                .andExpect(status().isNotFound())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));

        verify(clienteService).findClienteById(anyLong());

    }

}