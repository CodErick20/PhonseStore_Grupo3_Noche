package com.phoneapi.service;

import com.phoneapi.model.Categoria;
import com.phoneapi.model.Producto;

import java.util.List;

public interface iProductoServicio {
    List<Producto> listarProducto();
    Producto agregarProducto(Producto producto);
    void eliminarProducto(Integer id_producto);
    Producto listarProductoPorId(Integer id_producto);

}
