package com.phoneapi.service;

import com.phoneapi.model.Categoria;

import java.util.List;

public interface iCategoriaServicio {
    List<Categoria> listarCategoria();
    Categoria agregarCategoria(Categoria categoria);
    void eliminarCategoria(Integer id_categoria);
    Categoria listarCategoriaPorId(Integer id_categoria);
}
