package com.phoneapi.serviceImppl;

import com.phoneapi.model.Carrito;
import com.phoneapi.repository.CarritoRepositorio;
import com.phoneapi.service.iCarritoServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class CarritoServicio implements iCarritoServicio {

    @Autowired
    CarritoRepositorio repositorio;

    @Override
    public List<Carrito> listarCarrito() {
        return repositorio.findAll();
    }

    @Override
    public Carrito agregarCarrito(Carrito carrito) {
        return repositorio.save(carrito);
    }

    @Override
    public Carrito actualziarCarrito(Carrito carrito) {
        return repositorio.save(carrito);
    }

    @Override
    public void eliminarCarritoPorId(Integer id_carrito) {
        repositorio.deleteById(id_carrito);
    }

    @Override
    public Carrito buscarCarritoPorId(Integer id_carrito) {
        return repositorio.findById(id_carrito).orElse(null);
    }
}
