package com.laboratorio.services;

import com.laboratorio.models.Cliente;
import com.laboratorio.models.Factura;
import com.laboratorio.models.Producto;

import java.util.Date;
import java.util.List;

public interface ClienteService {

    List<Cliente> findAllClientes();
    Cliente saveCliente(Cliente cliente);
    Cliente findClienteById(Long id);
    void deleteClienteById(Long id);
    Producto findProductoById(Long id);
    Factura saveFactura(Factura factura);
    Factura findFacturaByFecha(Date fecha);
    List<Factura> findAllFacturas();
}
