package com.tenpo.pruebatenpo.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public class CrearTransaccionDTO {

    @NotNull
    @Schema(description = "Monto de la transacción")
    private int montoTransaccion;

    @NotNull
    @Schema(description = "Giro del comercio")
    private String giroComercio;

    @NotNull
    @Schema(description = "Nombre del tenpista")
    private String nombreTenpista;

    @NotNull
    @Schema(description = "Fecha de la transacción")
    private LocalDateTime fechaTransaccion;

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