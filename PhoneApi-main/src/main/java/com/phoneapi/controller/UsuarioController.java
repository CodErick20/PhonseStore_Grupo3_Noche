package com.phoneapi.controller;


import com.phoneapi.model.Usuario;

import com.phoneapi.service.iUsuarioServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("usuario")
@CrossOrigin(origins = {"http://localhost:4200/"})
public class UsuarioController {

    @Autowired
    iUsuarioServicio servicio;

    @GetMapping("")
    public ResponseEntity<List<Usuario>> listarUsuario(){
        List<Usuario>us = servicio.listarUsuario();
        return new ResponseEntity<List<Usuario>>(us, HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<Usuario> agregarUsuario(@Validated @RequestBody Usuario usuario){
        Usuario us = servicio.agregarUsuario(usuario);
        return new ResponseEntity<Usuario>(us, HttpStatus.OK);
    }


    //    @PostMapping("")
//    public ResponseEntity<Void> registrar(@RequestBody Usuario usuario){
//        Usuario pro = servicio.agregarUsuario(usuario);
//        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(pro.getId_usuario()).toUri();
//        return ResponseEntity.created(uri).build();
//    }

    @PutMapping("")
    public ResponseEntity<Usuario> actualizarUsuario(@Validated @RequestBody Usuario usuario){
        Usuario us = servicio.actualizarUsuario(usuario);
        return new ResponseEntity<Usuario>(us, HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> eliminarUsuario(@PathVariable("id") @RequestBody @Validated Integer id_usuario) throws Exception {
        Usuario us = servicio.listarUsuarioPorId(id_usuario);
        if (us == null){
            throw new Exception ("No se encontre el id");
        }
        servicio.eliminarUsuarioPorId(id_usuario);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<Usuario>listarUsuarioPorId(@PathVariable("id") Integer id_usuario) throws Exception {
        Usuario us = servicio.listarUsuarioPorId(id_usuario);
        if (us == null){
            throw  new Exception("no se encontre el id");
        }
        return new ResponseEntity<Usuario>(us, HttpStatus.OK);

    }
}
