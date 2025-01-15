package com.tenpo.pruebatenpo.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
public class Transaccion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idTransaccion;

    @NotNull
    private int montoTransaccion;

    @NotNull
    private String giroComercio;

    @NotNull
    private String nombreTenpista;

    @NotNull
    private LocalDateTime fechaTransaccion;

    public Long getIdTransaccion() {
        return idTransaccion;
    }

    public void setIdTransaccion(Long idTransaccion) {
        this.idTransaccion = idTransaccion;
    }

    public int getMontoTransaccion() {
        return montoTransaccion;
    }

    public void setMontoTransaccion(int montoTransaccion) {
        this.montoTransaccion = montoTransaccion;
    }

    public String getGiroComercio() {
        return giroComercio;
    }

    public void setGiroComercio(String giroComercio) {
        this.giroComercio = giroComercio;
    }

    public String getNombreTenpista() {
        return nombreTenpista;
    }

    public void setNombreTenpista(String nombreTenpista) {
        this.nombreTenpista = nombreTenpista;
    }

    public LocalDateTime getFechaTransaccion() {
        return fechaTransaccion;
    }

    public void setFechaTransaccion(LocalDateTime fechaTransaccion) {
        this.fechaTransaccion = fechaTransaccion;
    }
}