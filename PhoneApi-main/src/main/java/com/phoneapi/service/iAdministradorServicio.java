package com.phoneapi.service;

import com.phoneapi.model.Administrador;

import java.util.List;

public interface iAdministradorServicio {
    List<Administrador> listarAdministrador();

    Administrador agregarAdministrador(Administrador administrador);

    Administrador actualziarAdministrador(Administrador administrador);

    void eliminarAdministradorPorId(Integer id_administrador);

    Administrador buscarAdministradorPorId(Integer id_administrador);
}
