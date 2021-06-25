package com.laboratorio.services;

import com.laboratorio.Datos;
import com.laboratorio.models.Cliente;
import com.laboratorio.repositories.ClienteRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ClienteServiceImplTest {

    @Mock
    ClienteRepository repository;

    @InjectMocks
    ClienteServiceImpl service;


    @Test
    void testFindById() {
        when(repository.findById(anyLong())).thenReturn(Datos.CLIENTES.stream().findFirst());

        Cliente cliente = service.findClienteById(1L);
        assertEquals(cliente.getNombre(), Datos.CLIENTES.get(0).getNombre());
    }


    @Test
    void testDeleteById() {
        doNothing().when(repository).deleteById(anyLong());

        service.deleteClienteById(1L);
        verify(repository,times(1)).deleteById(1L);
    }
}