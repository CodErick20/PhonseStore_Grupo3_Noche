package com.phoneapi.service;

import com.phoneapi.model.Usuario;

import java.util.List;

public interface iUsuarioServicio {
    List<Usuario> listarUsuario();
    Usuario agregarUsuario(Usuario usuario);
    Usuario actualizarUsuario(Usuario usuario);
    void eliminarUsuarioPorId(Integer id_usuario);
    Usuario listarUsuarioPorId(Integer id_usuario);
}
