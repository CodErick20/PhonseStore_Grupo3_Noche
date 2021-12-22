package com.phoneapi.serviceImppl;


import com.phoneapi.model.Administrador;
import com.phoneapi.repository.AdministradorRepositorio;
import com.phoneapi.service.iAdministradorServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdministradorServicio implements iAdministradorServicio {

    @Autowired
    AdministradorRepositorio repositorio;
    @Override
    public List<Administrador> listarAdministrador() {
        return repositorio.findAll();
    }

    @Override
    public Administrador agregarAdministrador(Administrador administrador) {
        return repositorio.save(administrador);
    }

    @Override
    public Administrador actualziarAdministrador(Administrador administrador) {
        return repositorio.save(administrador);
    }

    @Override
    public void eliminarAdministradorPorId(Integer id_administrador) {
        repositorio.deleteById(id_administrador);
    }

    @Override
    public Administrador buscarAdministradorPorId(Integer id_administrador) {
        return repositorio.findById(id_administrador).orElse(null);
    }
}
