package com.tenpo.pruebatenpo.service;

import com.tenpo.pruebatenpo.model.Transaccion;
import com.tenpo.pruebatenpo.repository.TransaccionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class TransaccionService {

    @Autowired
    private TransaccionRepository transaccionRepository;

    public List<Transaccion> getAllTransacciones() {
        return transaccionRepository.findAll();
    }

    public Optional<Transaccion> getTransaccionById(Long id) {
        return transaccionRepository.findById(id);
    }

    public Transaccion createTransaccion(Transaccion transaccion) {
        transaccion.setFechaTransaccion(LocalDateTime.now()); // Establece la fecha y hora actuales
        return transaccionRepository.save(transaccion);
    }

    public Optional<Transaccion> updateTransaccion(Long id, Transaccion transaccionDetails) {
        return transaccionRepository.findById(id).map(transaccion -> {
            transaccion.setMontoTransaccion(transaccionDetails.getMontoTransaccion());
            transaccion.setGiroComercio(transaccionDetails.getGiroComercio());
            transaccion.setNombreTenpista(transaccionDetails.getNombreTenpista());
            transaccion.setFechaTransaccion(LocalDateTime.now());
            return transaccionRepository.save(transaccion);
        });
    }

    public boolean deleteTransaccion(Long id) {
        if (transaccionRepository.existsById(id)) {
            transaccionRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }
}