package com.tenpo.pruebatenpo.model;

import com.tenpo.pruebatenpo.dto.CrearTransaccionDTO;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;
import io.swagger.v3.oas.annotations.media.Schema;

@Entity
public class Transaccion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "ID de la transacción", accessMode = Schema.AccessMode.READ_ONLY)
    private Long idTransaccion;

    @NotNull(message = "El monto de la transacción no puede ser nulo")
    @Schema(description = "Monto de la transacción")
    private int montoTransaccion;

    @NotNull(message = "El giro del comercio no puede ser nulo")
    @Schema(description = "Giro del comercio")
    private String giroComercio;

    @NotNull(message = "El nombre del tenpista no puede ser nulo")
    @Schema(description = "Nombre del tenpista")
    private String nombreTenpista;

    @NotNull(message = "La fecha de la transacción no puede ser nula")
    @Schema(description = "Fecha de la transacción")
    private LocalDateTime fechaTransaccion;

    public Transaccion() {}

    public Transaccion(CrearTransaccionDTO dto) {
        this.montoTransaccion = dto.getMontoTransaccion();
        this.giroComercio = dto.getGiroComercio();
        this.nombreTenpista = dto.getNombreTenpista();
        this.fechaTransaccion = dto.getFechaTransaccion();
    }

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