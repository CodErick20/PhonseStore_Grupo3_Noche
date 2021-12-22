package com.phoneapi.serviceImppl;

import com.phoneapi.model.Producto;
import com.phoneapi.repository.ProductoRepositorio;
import com.phoneapi.service.iProductoServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductoServicio implements iProductoServicio {
    @Autowired
    ProductoRepositorio repositorio;

    @Override
    public List<Producto> listarProducto() {
        return repositorio.findAll();
    }

    @Override
    public Producto agregarProducto(Producto producto) {
        return repositorio.save(producto);
    }

    @Override
    public Producto actualizarProducto(Producto producto) {
        return repositorio.save(producto);
    }

    @Override
    public void eliminarProductoPorId(Integer id_producto) {
        repositorio.deleteById(id_producto);
    }

    @Override
    public Producto buscarProductoPorId(Integer id_producto) {
        return repositorio.findById(id_producto).orElse(null);
    }
}
