package com.phoneapi.controller;


import com.phoneapi.model.Administrador;
import com.phoneapi.service.iAdministradorServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("administrador")
public class AdministradorControlador {

    @Autowired
    iAdministradorServicio servicio;

    @GetMapping("")
    public ResponseEntity<List<Administrador>> listarAdministrador() {
        List<Administrador> ad = servicio.listarAdministrador();
        return new ResponseEntity<List<Administrador>>(ad, HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<Administrador> agregarAdministrador(@Validated @RequestBody Administrador administrador) {
        Administrador ad = servicio.agregarAdministrador(administrador);
        return new ResponseEntity<Administrador>(ad, HttpStatus.OK);
    }

    @PutMapping("")
    public ResponseEntity<Administrador> actualziarAdministrador(@Validated @RequestBody Administrador administrador) {
        Administrador ad = servicio.actualziarAdministrador(administrador);
        return new ResponseEntity<Administrador>(ad, HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> eliminarAdministrador(@Validated @RequestBody @PathVariable("id") Integer id_administrador) throws Exception {
        Administrador ad = servicio.buscarAdministradorPorId(id_administrador);
        if (ad == null) {
            throw new Exception("No se encontrol el id");
        }
        servicio.eliminarAdministradorPorId(id_administrador);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<Administrador> listarAdministradorPorId(@Validated @RequestBody @PathVariable("id") Integer id_administrador) throws Exception {
        Administrador ad = servicio.buscarAdministradorPorId(id_administrador);
        if (ad == null) {
            throw new Exception("No se encontro el id");
        }
        return new ResponseEntity<Administrador>(ad, HttpStatus.OK);
    }
}
