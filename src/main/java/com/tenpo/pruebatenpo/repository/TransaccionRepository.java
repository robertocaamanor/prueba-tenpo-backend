package com.tenpo.pruebatenpo.repository;

import com.tenpo.pruebatenpo.model.Transaccion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransaccionRepository extends JpaRepository<Transaccion, Long> {
}