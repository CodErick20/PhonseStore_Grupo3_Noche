package com.phoneapi.service;

import com.phoneapi.model.Cliente;

import java.util.List;

public interface iClienteServicio {
    List<Cliente> listarCliente();

    Cliente agregarCliente(Cliente cliente);

    Cliente actualzarCliente(Cliente cliente);

    void eliminarCliente(Integer id_cliente);

    Cliente buscarrClientePorId(Integer id_cliente);

}
