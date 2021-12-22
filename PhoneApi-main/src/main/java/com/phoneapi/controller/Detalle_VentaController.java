package com.phoneapi.controller;


import com.phoneapi.model.Detalle_Venta;
import com.phoneapi.service.iDetalle_VentaServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/detalle_venta")
public class Detalle_VentaController {
    @Autowired
    iDetalle_VentaServicio servicio;

    @GetMapping("")
    public ResponseEntity<List<Detalle_Venta>> listarDetalle_Venta() {
        List<Detalle_Venta> dt = servicio.listarDetalle_Venta();
        return new ResponseEntity<List<Detalle_Venta>>(dt, HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<Detalle_Venta> agregarDetalle_Venta(@Validated @RequestBody Detalle_Venta detalle_venta) {
        Detalle_Venta dt = servicio.agregarDetalle_venta(detalle_venta);
        return new ResponseEntity<Detalle_Venta>(dt, HttpStatus.OK);
    }

    @PutMapping("")
    public ResponseEntity<Detalle_Venta> actualizarDetalle_Venta(@Validated @RequestBody Detalle_Venta detalle_venta) {
        Detalle_Venta dt = servicio.actualizarDetalle_venta(detalle_venta);
        return new ResponseEntity<Detalle_Venta>(dt, HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> eliminarDetalle_Venta(@Validated @RequestBody @PathVariable("id") Integer id_detalle_venta) throws Exception {
        Detalle_Venta dt = servicio.buscarDetalleVentaPorId(id_detalle_venta);
        if (dt == null) {
            throw new Exception("No se encontro el id");
        }
        servicio.eliminarDetalle_Venta(id_detalle_venta);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<Detalle_Venta> listarDetalleVentaPorId(@Validated @RequestBody @PathVariable("id") Integer id) throws Exception {
        Detalle_Venta dt = servicio.buscarDetalleVentaPorId(id);
        if (dt == null) {
            throw new Exception("No se entro el id");
        }
        return new ResponseEntity<Detalle_Venta>(dt, HttpStatus.OK);
    }
}
