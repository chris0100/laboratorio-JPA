package com.laboratorio.services;

import com.laboratorio.models.Cliente;
import com.laboratorio.models.Factura;
import com.laboratorio.models.Producto;
import com.laboratorio.repositories.ClienteRepository;
import com.laboratorio.repositories.FacturaRepository;
import com.laboratorio.repositories.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ClienteServiceImpl implements ClienteService{


    @Autowired
    ClienteRepository clienteRepository;

    @Autowired
    FacturaRepository facturaRepository;

    @Autowired
    ProductoRepository productoRepository;


    @Override
    public List<Cliente> findAllClientes() {
        return clienteRepository.findAll();
    }

    @Override
    public Cliente saveCliente(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    @Override
    public Cliente findClienteById(Long id) {
        return clienteRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteClienteById(Long id) {
        clienteRepository.deleteById(id);
    }

    @Override
    public Producto findProductoById(Long id) {
        return productoRepository.findById(id).orElse(null);
    }

    @Override
    public Factura saveFactura(Factura factura) {
        return facturaRepository.save(factura);
    }

    @Override
    public Factura findFacturaByFecha(Date fecha) {
        return facturaRepository.findFacturaByCreateAt(fecha);
    }

    @Override
    public List<Factura> findAllFacturas() {
        return facturaRepository.findAll();
    }
}
