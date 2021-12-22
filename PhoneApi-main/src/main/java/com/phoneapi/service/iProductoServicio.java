package com.phoneapi.service;

import com.phoneapi.model.Producto;

import java.util.List;

public interface iProductoServicio {
    List<Producto> listarProducto();

    Producto agregarProducto(Producto producto);

    Producto actualizarProducto(Producto producto);

    void eliminarProductoPorId(Integer id_producto);

    Producto buscarProductoPorId(Integer id_producto);

}
