package com.phoneapi.controller;


import com.phoneapi.model.Carrito;
import com.phoneapi.service.iCarritoServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/carrito")
@CrossOrigin(origins = {"http://localhost:4200/"})
public class CarritoControlador {

    @Autowired
    iCarritoServicio servicio;

    @GetMapping("")
    public ResponseEntity<List<Carrito>> listarCarrito(){
        List<Carrito> car = servicio.listarCarrito();
        return  new ResponseEntity<List<Carrito>>(car, HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<Carrito> agregarCarrito(@Validated @RequestBody Carrito carrito) {
        Carrito car = servicio.agregarCarrito(carrito);
        return new ResponseEntity<Carrito>(car, HttpStatus.OK);
    }

    @PutMapping("")
    public ResponseEntity<Carrito> actualizarCarrito(@Validated @RequestBody Carrito carrito){
        Carrito car = servicio.actualziarCarrito(carrito);
        return new ResponseEntity<Carrito>(car, HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> eliminarCarrito(@Validated @RequestBody @PathVariable("id") Integer id_carrito) throws Exception {
        Carrito car = servicio.buscarCarritoPorId(id_carrito);
        if (car == null ) {
            throw new Exception("no se encontre el id");
        }
        servicio.eliminarCarritoPorId(id_carrito);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<Carrito> listarCarriroPorId(@Validated @RequestBody @PathVariable("id") Integer id_carrito) throws Exception {
        Carrito car = servicio.buscarCarritoPorId(id_carrito);
        if (car == null) {
            throw new Exception("No se encontre el id");
        }
        return new ResponseEntity<Carrito>(car, HttpStatus.OK);
    }
}
