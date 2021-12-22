package com.phoneapi.service;

import com.phoneapi.model.Categoria;
import org.springframework.data.domain.Page;

import java.awt.print.Pageable;
import java.util.List;

public interface iCategoriaServicio {
    List<Categoria> listarCategoria();

//    /*paginacion*/
//    public Page<Categoria> listarCategoria(Pageable pageable);

    Categoria agregarCategoria(Categoria categoria);

    void eliminarCategoria(Integer id_categoria);

    Categoria listarCategoriaPorId(Integer id_categoria);
}
