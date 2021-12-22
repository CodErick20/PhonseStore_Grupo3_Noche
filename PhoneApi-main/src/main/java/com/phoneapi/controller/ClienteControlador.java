package com.phoneapi.controller;


import com.phoneapi.model.Cliente;
import com.phoneapi.service.iClienteServicio;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cliente")
public class ClienteControlador {
    @Autowired
    iClienteServicio servicio;

    @GetMapping("")
    public ResponseEntity<List<Cliente>> listarCliente(){
        List<Cliente> cl = servicio.listarCliente();
        return new ResponseEntity<List<Cliente>>(cl, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Cliente> agregarCliente(@Validated @RequestBody Cliente cliente){
        Cliente cl = servicio.agregarCliente(cliente);
        return new ResponseEntity<Cliente>(cl, HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<Cliente> actualizarCliente(@Validated @RequestBody Cliente cliente){
        Cliente cl = servicio.actualzarCliente(cliente);
        return new ResponseEntity<Cliente>(cl, HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> eliminarCliente(@Validated @RequestBody @PathVariable("id") Integer id_cliente) throws Exception {
        Cliente cl = servicio.buscarrClientePorId(id_cliente);
        if (cl == null) {
            throw new Exception("No se encontro el id");
        }
        servicio.eliminarCliente(id_cliente);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<Cliente> listarClientePorId(@Validated @RequestBody @PathVariable("id")Integer id) throws Exception {
        Cliente cl = servicio.buscarrClientePorId(id);
        if (cl == null) {
            throw new Exception("No se encontro el id");
        }
        return new ResponseEntity<Cliente>(cl, HttpStatus.OK);

    }
}
