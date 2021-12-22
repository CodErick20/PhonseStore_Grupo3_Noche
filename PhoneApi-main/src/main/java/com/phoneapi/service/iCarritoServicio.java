package com.phoneapi.service;

import com.phoneapi.model.Carrito;

import java.util.List;

public interface iCarritoServicio {
    List<Carrito> listarCarrito();
    Carrito agregarCarrito(Carrito carrito);

    Carrito actualziarCarrito(Carrito carrito);

    void eliminarCarritoPorId(Integer id_carrito);

    Carrito buscarCarritoPorId(Integer id_carrito);
}
