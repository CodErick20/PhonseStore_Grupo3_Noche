package com.phoneapi.controller;


import com.phoneapi.model.Producto;

import com.phoneapi.service.iProductoServicio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/producto")
@CrossOrigin(origins = "http://localhost:4200")
public class ProductoControllador {

    @Autowired
    iProductoServicio servicio;

    @GetMapping("")
    public ResponseEntity<List<Producto>> listarProducto(){
        List<Producto> pro = servicio.listarProducto();
        return new ResponseEntity<List<Producto>>(pro, HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<?> create(@RequestBody Producto producto){
        Producto productoNew = null;
        Map<String, Object> response = new HashMap<>();
        try{
            productoNew = servicio.agregarProducto(producto);
        }catch(DataAccessException e){
            response.put("mensaje", "Erro al realizar el insert en la base de datos");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.put("mensaje", "La Producto ha sido creada con exito!");
        response.put("producto", productoNew);
        return new ResponseEntity<Map<String, Object>> (response, HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    public ResponseEntity<?> actualizar(@RequestBody Producto producto, @PathVariable("id") Integer id_producto){
        Producto productoActual = servicio.listarProductoPorId(id_producto);
        Producto categoriaUpdate = null;

        Map<String, Object> response = new HashMap<>();
        if (productoActual == null){
            response.put("mensaje", "Error, nose pudo editar, la producto ID: ".concat(id_producto.toString().concat(" no existe en la base de datos")));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }
        try {
            productoActual.setEstado(producto.getEstado());
            productoActual.setNombre(producto.getNombre());
            categoriaUpdate= servicio.agregarProducto(productoActual);
        }catch (DataAccessException e){
            response.put("mensaje", "Error al actualzar la producto en la base de datos");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.put("mensaje", "La Producto ha sido actualzada con exito!");
        response.put("producto", categoriaUpdate);
        return new ResponseEntity<Map<String, Object>> (response, HttpStatus.CREATED);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> eliminar(@PathVariable("id") Integer id) throws Exception {
        Map<String, Object> response = new HashMap<>();
        try {
            Producto producto = servicio.listarProductoPorId(id);

            String nombreFotoAnterior = producto.getUrl_imagen();

            if (nombreFotoAnterior != null && nombreFotoAnterior.length() > 0){
                Path rutaFotoanterior = Paths.get("uploads"). resolve(nombreFotoAnterior).toAbsolutePath();
                File archivoFotoAnterior = rutaFotoanterior.toFile();
                if (archivoFotoAnterior.exists() && archivoFotoAnterior.canRead()){
                    archivoFotoAnterior.delete();
                }
            }

            servicio.eliminarProducto(id);
        }catch (DataAccessException e) {
            response.put("mensaje", "Error al eliminar la producto en la base de datos");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.put("mensaje", "La Producto ha sido eliminada con exito!");;
        return new ResponseEntity<Map<String, Object>>(response,HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<?> listarPaisPorId(@PathVariable("id") Integer id) {
        Producto obj = null;
        Map<String, Object> response = new HashMap<>();
        try {
            obj = servicio.listarProductoPorId(id);
        } catch(DataAccessException e){
            response.put("mensae", "Erro al realizar la consulta en la base de datos");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        if (obj == null){
            response.put("mensaje", "La Producto ID: ".concat(id.toString().concat(" no existe en la base de datos")));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Producto>(obj,HttpStatus.OK);
    }

    @PostMapping("/upload")
    public ResponseEntity<?>upload(@RequestParam("archivo") MultipartFile archivo, @RequestParam("id") Integer id){
        /*map para pasar mensajes al usuario*/
        Map<String, Object> response = new HashMap<>();
        Producto producto = servicio.listarProductoPorId(id);
        if (!archivo.isEmpty()){
//            UUID.randomUUID().toString() + "_" +
            String nombreArchivo =  archivo.getOriginalFilename().replace(" ","");
            Path rutaArchivo = Paths.get("uploads").resolve(nombreArchivo).toAbsolutePath();

            try {
                Files.copy(archivo.getInputStream(),rutaArchivo);
            } catch (IOException e) {
                response.put("mensaje", "Error al subir la imagen " + nombreArchivo);
                response.put("error", e.getMessage().concat(": ").concat(e.getCause().getMessage()));
                return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
            }
            String nombreFotoAnterior = producto.getUrl_imagen();
            if (nombreFotoAnterior != null && nombreFotoAnterior.length() > 0){
                Path rutaFotoanterior = Paths.get("uploads"). resolve(nombreFotoAnterior).toAbsolutePath();
                File archivoFotoAnterior = rutaFotoanterior.toFile();
                if (archivoFotoAnterior.exists() && archivoFotoAnterior.canRead()){
                    archivoFotoAnterior.delete();
                }
            }
            producto.setUrl_imagen(nombreArchivo);
            servicio.agregarProducto(producto);
            /*pasar mensaje al json*/
            response.put("producto", producto);
            response.put("mensaje", "Has subido correctamente la imagen " + nombreArchivo);
        }
        return new ResponseEntity<Map<String, Object>> (response, HttpStatus.CREATED);
    }

    @GetMapping("/uploads/img/{nombreFoto:.+}")
    public ResponseEntity<Resource>verFoto(@PathVariable String nombreFoto){
        Path rutaArchivo = Paths.get("uploads"). resolve(nombreFoto).toAbsolutePath();
        Resource recurso = null;
        try {
            recurso = new UrlResource(rutaArchivo.toUri());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        if (!recurso.exists() && !recurso.isReadable()){
            throw  new RuntimeException("No se pudo cargar la imagen con el nombre de la foto");
        }
        HttpHeaders cabecera = new HttpHeaders();
        cabecera.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + recurso.getFilename() + "\"");
        return new ResponseEntity<Resource>(recurso,cabecera, HttpStatus.OK);
    }
}
