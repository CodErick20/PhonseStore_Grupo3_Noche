package com.phoneapi.service;

import com.phoneapi.model.Detalle_Venta;

import java.util.List;

public interface iDetalle_VentaServicio {
    List<Detalle_Venta> listarDetalle_Venta();

    Detalle_Venta agregarDetalle_venta(Detalle_Venta detalle_venta);

    Detalle_Venta actualizarDetalle_venta(Detalle_Venta detalle_venta);

    void eliminarDetalle_Venta(Integer id_detalle_venta);

    Detalle_Venta buscarDetalleVentaPorId(Integer id_detalle_venta);

}
