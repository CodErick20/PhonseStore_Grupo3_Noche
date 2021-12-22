package com.phoneapi.serviceImppl;

import com.phoneapi.model.Producto;
import com.phoneapi.repository.ProductoRepositorio;
import com.phoneapi.service.iProductoServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class ProductoServicio implements iProductoServicio {

    @Autowired
    ProductoRepositorio repositorio;

    @Override
    @Transactional
    public List<Producto> listarProducto() {
        return repositorio.findAll();
    }

    @Override
    @Transactional
    public Producto agregarProducto(Producto producto) {
        return repositorio.save(producto);
    }

    @Override
    public void eliminarProducto(Integer id_producto) {
        repositorio.deleteById(id_producto);
    }

    @Override
    public Producto listarProductoPorId(Integer id_producto) {
        return repositorio.findById(id_producto).orElse(null);
    }
}
