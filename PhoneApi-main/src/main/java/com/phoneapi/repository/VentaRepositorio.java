package com.phoneapi.repository;

import com.phoneapi.model.Venta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VentaRepositorio extends JpaRepository<Venta, Integer> {
}
