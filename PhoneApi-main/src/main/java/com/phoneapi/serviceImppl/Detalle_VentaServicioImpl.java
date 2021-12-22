package com.phoneapi.serviceImppl;

import com.phoneapi.model.Detalle_Venta;
import com.phoneapi.repository.Detalle_VentaRepositorio;
import com.phoneapi.service.iDetalle_VentaServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Detalle_VentaServicioImpl implements iDetalle_VentaServicio {

    @Autowired
    Detalle_VentaRepositorio repositorio;
    @Override
    public List<Detalle_Venta> listarDetalle_Venta() {
        return repositorio.findAll();
    }

    @Override
    public Detalle_Venta agregarDetalle_venta(Detalle_Venta detalle_venta) {
        return repositorio.save(detalle_venta);
    }

    @Override
    public Detalle_Venta actualizarDetalle_venta(Detalle_Venta detalle_venta) {
        return repositorio.save(detalle_venta);
    }

    @Override
    public void eliminarDetalle_Venta(Integer id_detalle_venta) {
        repositorio.deleteById(id_detalle_venta);
    }

    @Override
    public Detalle_Venta buscarDetalleVentaPorId(Integer id_detalle_venta) {
        return repositorio.findById(id_detalle_venta).orElse(null);
    }

}
