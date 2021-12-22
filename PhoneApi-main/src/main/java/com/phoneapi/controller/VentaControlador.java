package com.phoneapi.controller;


import com.phoneapi.model.Venta;
import com.phoneapi.service.iVentaServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/venta")
public class VentaControlador {
    @Autowired
    iVentaServicio servicio;

    @GetMapping("")
    public ResponseEntity<List<Venta>> listarVenta() {
        List<Venta> ve = servicio.listarVenta();
        return new ResponseEntity<List<Venta>>(ve, HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<Venta> agregarVenta(@Validated @RequestBody Venta venta) {
        Venta ve = servicio.agregarVenta(venta);
        return new ResponseEntity<Venta>(ve, HttpStatus.OK);
    }

    @PutMapping("")
    public ResponseEntity<Venta> actualizarVenta(@Validated @RequestBody Venta venta) {
        Venta ve = servicio.actualzarVenta(venta);
        return new ResponseEntity<Venta>(ve, HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> eliminarVenta(@Validated @RequestBody @PathVariable("id") Integer id_venta) throws Exception {
        Venta ve = servicio.buscarVentaPorId(id_venta);
        if (ve == null) {
            throw new Exception("No se encontro el id");
        }
        servicio.eliminarVenta(id_venta);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<Venta> listarVentaPorId(@Validated @RequestBody @PathVariable("id") Integer id_venta) throws Exception {
        Venta ve = servicio.buscarVentaPorId(id_venta);
        if (ve == null) {
            throw new Exception("No se encontro el id");
        }
        return new ResponseEntity<Venta>(ve, HttpStatus.OK);
    }
}
