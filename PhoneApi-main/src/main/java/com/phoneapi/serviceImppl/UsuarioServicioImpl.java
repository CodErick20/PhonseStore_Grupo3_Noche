package com.phoneapi.serviceImppl;

import com.phoneapi.model.Usuario;
import com.phoneapi.repository.UsuarioRepositorio;
import com.phoneapi.service.iUsuarioServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioServicioImpl implements iUsuarioServicio {
    @Autowired
    UsuarioRepositorio repositorio;

    @Override
    public List<Usuario> listarUsuario() {
        return repositorio.findAll();
    }

    @Override
    public Usuario agregarUsuario(Usuario usuario) {
        return repositorio.save(usuario);
    }

    @Override
    public Usuario actualizarUsuario(Usuario usuario) {
        return repositorio.save(usuario);
    }

    @Override
    public void eliminarUsuarioPorId(Integer id_usuario) {
        repositorio.deleteById(id_usuario);
    }

    @Override
    public Usuario listarUsuarioPorId(Integer id_usuario) {
        return repositorio.findById(id_usuario).orElse(null);
    }
}
