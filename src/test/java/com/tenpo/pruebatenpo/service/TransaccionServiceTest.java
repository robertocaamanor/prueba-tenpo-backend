package com.tenpo.pruebatenpo.service;

import com.tenpo.pruebatenpo.model.Transaccion;
import com.tenpo.pruebatenpo.repository.TransaccionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TransaccionServiceTest {

    @Mock
    private TransaccionRepository transaccionRepository;

    @InjectMocks
    private TransaccionService transaccionService;

    private Transaccion transaccion;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        transaccion = new Transaccion();
        transaccion.setIdTransaccion(1L);
        transaccion.setMontoTransaccion(100);
        transaccion.setGiroComercio("Retail");
        transaccion.setNombreTenpista("John Doe");
        transaccion.setFechaTransaccion(LocalDateTime.now());
    }

    @Test
    void testGetAllTransacciones() {
        when(transaccionRepository.findAll()).thenReturn(Arrays.asList(transaccion));
        List<Transaccion> transacciones = transaccionService.getAllTransacciones();
        assertEquals(1, transacciones.size());
        verify(transaccionRepository, times(1)).findAll();
    }

    @Test
    void testGetTransaccionById() {
        when(transaccionRepository.findById(1L)).thenReturn(Optional.of(transaccion));
        Optional<Transaccion> foundTransaccion = transaccionService.getTransaccionById(1L);
        assertTrue(foundTransaccion.isPresent());
        assertEquals(transaccion.getIdTransaccion(), foundTransaccion.get().getIdTransaccion());
        verify(transaccionRepository, times(1)).findById(1L);
    }

    @Test
    void testCreateTransaccion() {
        when(transaccionRepository.save(transaccion)).thenReturn(transaccion);
        Transaccion createdTransaccion = transaccionService.createTransaccion(transaccion);
        assertEquals(transaccion.getIdTransaccion(), createdTransaccion.getIdTransaccion());
        verify(transaccionRepository, times(1)).save(transaccion);
    }

    @Test
    void testUpdateTransaccion() {
        Transaccion updatedDetails = new Transaccion();
        updatedDetails.setMontoTransaccion(200);
        updatedDetails.setGiroComercio("Tech");
        updatedDetails.setNombreTenpista("Jane Doe");
        updatedDetails.setFechaTransaccion(LocalDateTime.now());

        when(transaccionRepository.findById(1L)).thenReturn(Optional.of(transaccion));
        when(transaccionRepository.save(transaccion)).thenReturn(transaccion);

        Optional<Transaccion> updatedTransaccion = transaccionService.updateTransaccion(1L, updatedDetails);
        assertTrue(updatedTransaccion.isPresent());
        assertEquals(updatedDetails.getMontoTransaccion(), updatedTransaccion.get().getMontoTransaccion());
        verify(transaccionRepository, times(1)).findById(1L);
        verify(transaccionRepository, times(1)).save(transaccion);
    }

    @Test
    void testDeleteTransaccion() {
        when(transaccionRepository.existsById(1L)).thenReturn(true);
        boolean isDeleted = transaccionService.deleteTransaccion(1L);
        assertTrue(isDeleted);
        verify(transaccionRepository, times(1)).existsById(1L);
        verify(transaccionRepository, times(1)).deleteById(1L);
    }
}