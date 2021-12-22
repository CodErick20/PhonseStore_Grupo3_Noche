package com.phoneapi.controller;

import com.phoneapi.model.Categoria;
import com.phoneapi.service.iCategoriaServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/categoria")
@CrossOrigin(origins = "http://localhost:4200/")
public class CategoriaController {

    @Autowired
    iCategoriaServicio servicio;

    @GetMapping("")
    public List<Categoria> listarCategoria(){
        return servicio.listarCategoria();
    }

//    /*paginacion*/
//    @GetMapping("/categoria/page/{page}")
//    public Page<Categoria> listarCategoria(@PathVariable Integer page) {
//        Pageable pageable = PageRequest.of(page, 4);
//        return servicio.listarCategoria(pageable);
//    }

    @PostMapping("")
    /*binding contiene los mensajes de error para saber si hay algun problema*/
    public ResponseEntity<?> create(@Valid @RequestBody Categoria categoria, BindingResult result){
        Categoria clienteNew = null;
        Map<String, Object> response = new HashMap<>();
        /*validar si contiene errores*/
        if (result.hasErrors()){
            /*la lista contiene los mensajes de error*/
            List<String> errors = new ArrayList<>();
            for(FieldError err: result.getFieldErrors()){
                errors.add("El Campo '" + err.getField()+ "' " + err.getDefaultMessage());
            }
            response.put("errors", errors);
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
        }
        try{
            clienteNew = servicio.agregarCategoria(categoria);
        }catch(DataAccessException e){
            response.put("mensaje", "Error al realizar el insert en la base de datos");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.put("mensaje", "La Categoria ha sido creada con exito!");
        response.put("categoria", clienteNew);
        return new ResponseEntity<Map<String, Object>> (response, HttpStatus.CREATED);
    }


    @PutMapping("{id}")
    public ResponseEntity<?> actualizar(@Valid @RequestBody Categoria categoria, BindingResult result, @PathVariable("id") Integer id_categoria){
        Categoria categoriaActual = servicio.listarCategoriaPorId(id_categoria);
        Categoria categoriaUpdate = null;
        Map<String, Object> response = new HashMap<>();
        /*validar si contiene errores*/
        if (result.hasErrors()){
            /*la lista contiene los mensajes de error*/
            List<String> errors = new ArrayList<>();
            for(FieldError err: result.getFieldErrors()){
                /*para validar json vacio*/
                errors.add("El Campo '" + err.getField()+ "' " + err.getDefaultMessage());
            }
            response.put("errors", errors);
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
        }
        if (categoriaActual == null){
            response.put("mensaje", "Error, nose pudo editar, la categoria ID: ".concat(id_categoria.toString().concat(" no existe en la base de datos")));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }
       try {
           categoriaActual.setEstado(categoria.getEstado());
           categoriaActual.setNombre(categoria.getNombre());
           categoriaUpdate= servicio.agregarCategoria(categoriaActual);
       }catch (DataAccessException e){
           response.put("mensaje", "Error al actualzar la categoria en la base de datos");
           response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
           return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
       }
        response.put("mensaje", "La Categoria ha sido actualzada con exito!");
        response.put("categoria", categoriaUpdate);
        return new ResponseEntity<Map<String, Object>> (response, HttpStatus.CREATED);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> eliminar(@PathVariable("id") Integer id) throws Exception {
        Map<String, Object> response = new HashMap<>();
        try {
            servicio.eliminarCategoria(id);
        }catch (DataAccessException e) {
            response.put("mensaje", "Error al eliminar la categoria en la base de datos");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.put("mensaje", "La Categoria ha sido eliminada con exito!");;
        return new ResponseEntity<Map<String, Object>>(response,HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<?> listarPaisPorId(@PathVariable("id") Integer id) {
        Categoria obj = null;
        Map<String, Object> response = new HashMap<>();
        try {
            obj = servicio.listarCategoriaPorId(id);
        } catch(DataAccessException e){
            response.put("mensae", "Erro al realizar la consulta en la base de datos");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        if (obj == null){
            response.put("mensaje", "La Categoria ID: ".concat(id.toString().concat(" no existe en la base de datos")));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Categoria>(obj,HttpStatus.OK);
    }
}
