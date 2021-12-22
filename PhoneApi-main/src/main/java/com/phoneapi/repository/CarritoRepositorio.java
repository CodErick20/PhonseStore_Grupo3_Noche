package com.phoneapi.repository;

import com.phoneapi.model.Carrito;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarritoRepositorio extends JpaRepository<Carrito, Integer> {
}
