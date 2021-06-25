package com.laboratorio.controllers;


import com.laboratorio.models.Cliente;
import com.laboratorio.models.DTO.ClienteDTO;
import com.laboratorio.services.ClienteService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/cliente")
public class ClienteController {


    @Autowired
    private ClienteService clienteService;

    @Autowired
    private ModelMapper modelMapper;


    @GetMapping("/ver/{id}")
    public ResponseEntity getCliente(@PathVariable("id") Long id) {
        Map<String, Object> response = new HashMap<>();
        Cliente cliente;

        try {
            cliente = clienteService.findClienteById(id);

            if (cliente == null) {
                response.put("error", "No se encuentra el cliente por el id " + id);
                return new ResponseEntity(response, HttpStatus.NOT_FOUND);
            }

            ClienteDTO clienteDTO = modelMapper.map(cliente, ClienteDTO.class);
            clienteDTO.setFacturas(new ArrayList<>());
            return ResponseEntity.ok(clienteDTO);

        } catch (DataAccessException err) {
            response.put("mensaje", "Error al consultar la base de datos");
            response.put("error", err.getMostSpecificCause().getMessage());
            return new ResponseEntity(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping("/ver")
    public ResponseEntity getClientes() {
        List<ClienteDTO> listaClientes;

        Map<String, Object> response = new HashMap<>();

        try {
            listaClientes = clienteService.findAllClientes()
                    .stream()
                    .map(obj -> {
                        obj.setFacturas(new ArrayList<>());
                        return modelMapper.map(obj, ClienteDTO.class);
                    }).collect(Collectors.toList());

            return ResponseEntity.ok(listaClientes);
        } catch (DataAccessException err) {
            response.put("mensaje", "Error al consultar la base de datos");
            response.put("error", err.getMostSpecificCause().getMessage());
            return new ResponseEntity(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @PostMapping("/save")
    public ResponseEntity createCliente(@Valid @RequestBody Cliente cliente, BindingResult result){
        Cliente clienteNuevo;

        Map<String, Object> response = new HashMap<>();

        if (result.hasErrors()){
            List<String> errors = result.getFieldErrors()
                    .stream()
                    .map(err -> err.getDefaultMessage())
                    .collect(Collectors.toList());

            response.put("error", errors);
            return new ResponseEntity(response, HttpStatus.BAD_REQUEST);
        }

        try{
            clienteNuevo = new Cliente();
            clienteNuevo.setApellido(cliente.getApellido());
            clienteNuevo.setNombre(cliente.getNombre());
            clienteNuevo.setCreateAt(new Date());
            clienteNuevo.setEmail(cliente.getEmail());
            clienteNuevo.setFacturas(new ArrayList<>());
            clienteNuevo.setFoto("sinfoto.jpg");

            Cliente clienteSaved = clienteService.saveCliente(clienteNuevo);
            response.put("mensaje", "El cliente "  + clienteSaved.getNombre() + " ha sido creado con exito");
            response.put("cliente", clienteSaved);
            return new ResponseEntity(response, HttpStatus.CREATED);

        } catch (DataAccessException err) {
            response.put("error", err.getMostSpecificCause().getMessage());
            return new ResponseEntity(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }



}



















