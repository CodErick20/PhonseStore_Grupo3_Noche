package com.phoneapi.controller;


import com.phoneapi.model.Categoria;
import com.phoneapi.model.Producto;
import com.phoneapi.service.iProductoServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/producto")
@CrossOrigin(origins = "http://localhost:4200/")
public class ProductoControllador {

    @Autowired
    iProductoServicio servicio;

    @GetMapping("")
    public ResponseEntity<List<Producto>> listarProducto(){
        List<Producto> pro = servicio.listarProducto();
        return new ResponseEntity<List<Producto>>(pro, HttpStatus.OK);
    }
/*
    @PostMapping("")
    public ResponseEntity<Producto> agregarProducto(@Validated @RequestBody Producto producto){
        Producto pro = servicio.agregarProducto(producto);
        return new ResponseEntity<Producto>(pro, HttpStatus.OK);
    }*/
    
    @PostMapping("")
    public ResponseEntity<?> create(@RequestBody Producto producto){
    	Producto clienteNew = null;
        Map<String, Object> response = new HashMap<>();
        try{
            clienteNew = servicio.agregarProducto(producto);
        }catch(DataAccessException e){
            response.put("mensaje", "Erro al realizar el insert en la base de datos");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.put("mensaje", "La Categoria ha sido creada con exito!");
        response.put("categoria", clienteNew);
        return new ResponseEntity<Map<String, Object>> (response, HttpStatus.CREATED);
    }
    

//    @PostMapping("")
//    public ResponseEntity<Void> registrar(@RequestBody Producto producto){
//        Producto pro = servicio.agregarProducto(producto);
//        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(pro.getId_producto()).toUri();
//        return ResponseEntity.created(uri).build();
//    }

    @PutMapping("{id}")
    public ResponseEntity<Producto> actualizarProducto(@RequestBody Producto producto){
        Producto pro = servicio.actualizarProducto(producto);
        return new ResponseEntity<Producto>(pro, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable("id") Integer id) throws Exception {
        Producto obj = servicio.buscarProductoPorId(id);
        if (obj == null){
            throw new Exception("No se encontro id");
        }
        servicio.eliminarProductoPorId(id);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<Producto> listarProductoPorId(@PathVariable("id") Integer id_producto) throws Exception {
        Producto pro = servicio.buscarProductoPorId(id_producto);
        if (pro == null){
            throw new Exception("No se encontro el id");
        }
        return new ResponseEntity<Producto>(pro, HttpStatus.OK);
    }
}
