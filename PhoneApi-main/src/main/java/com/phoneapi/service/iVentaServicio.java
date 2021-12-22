package com.phoneapi.service;

import com.phoneapi.model.Venta;

import java.util.List;

public interface iVentaServicio {
    List<Venta> listarVenta();

    Venta agregarVenta(Venta venta);

    Venta actualzarVenta(Venta venta);

    void eliminarVenta(Integer id_venta);

    Venta buscarVentaPorId(Integer id_venta);
}
