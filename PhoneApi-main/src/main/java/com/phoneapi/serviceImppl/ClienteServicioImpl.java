package com.phoneapi.serviceImppl;

import com.phoneapi.model.Cliente;
import com.phoneapi.repository.ClienteRepositorio;
import com.phoneapi.service.iClienteServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteServicioImpl implements iClienteServicio {

    @Autowired
    ClienteRepositorio repositorio;
    @Override
    public List<Cliente> listarCliente() {
        return repositorio.findAll();
    }

    @Override
    public Cliente agregarCliente(Cliente cliente) {
        return repositorio.save(cliente);
    }

    @Override
    public Cliente actualzarCliente(Cliente cliente) {
        return repositorio.save(cliente);
    }

    @Override
    public void eliminarCliente(Integer id_cliente) {
        repositorio.deleteById(id_cliente);
    }

    @Override
    public Cliente buscarrClientePorId(Integer id_cliente) {
        return repositorio.findById(id_cliente).orElse(null);
    }
}
