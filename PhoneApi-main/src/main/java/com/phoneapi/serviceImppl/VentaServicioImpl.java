package com.phoneapi.serviceImppl;


import com.phoneapi.model.Venta;
import com.phoneapi.repository.VentaRepositorio;
import com.phoneapi.service.iVentaServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VentaServicioImpl implements iVentaServicio {

    @Autowired
    VentaRepositorio repositorio;
    @Override
    public List<Venta> listarVenta() {
        return repositorio.findAll();
    }

    @Override
    public Venta agregarVenta(Venta venta) {
        return repositorio.save(venta);
    }

    @Override
    public Venta actualzarVenta(Venta venta) {
        return repositorio.save(venta);
    }

    @Override
    public void eliminarVenta(Integer id_venta) {
        repositorio.deleteById(id_venta);
    }

    @Override
    public Venta buscarVentaPorId(Integer id_venta) {
        return repositorio.findById(id_venta).orElse(null);
    }
}
