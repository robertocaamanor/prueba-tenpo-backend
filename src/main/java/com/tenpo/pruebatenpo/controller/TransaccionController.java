package com.tenpo.pruebatenpo.controller;

import com.tenpo.pruebatenpo.model.Transaccion;
import com.tenpo.pruebatenpo.service.TransaccionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/transaction")
@CrossOrigin
@Tag(name = "Transaccion Controller", description = "Operaciones relacionadas con transacciones")
public class TransaccionController {

    @Autowired
    private TransaccionService transaccionService;

    @GetMapping
    @Operation(summary = "Obtener todas las transacciones", description = "Recupera una lista de todas las transacciones")
    public List<Transaccion> getAllTransacciones() {
        return transaccionService.getAllTransacciones();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener transacción por ID", description = "Recupera una transacción por su ID")
    public ResponseEntity<Transaccion> getTransaccionById(@PathVariable Long id) {
        Optional<Transaccion> transaccion = transaccionService.getTransaccionById(id);
        return transaccion.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    @Operation(summary = "Crear una nueva transacción", description = "Crea una nueva transacción")
    public Transaccion createTransaccion(@RequestBody Transaccion transaccion) {
        return transaccionService.createTransaccion(transaccion);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar una transacción", description = "Actualiza una transacción existente por su ID")
    public ResponseEntity<Transaccion> updateTransaccion(@PathVariable Long id, @RequestBody Transaccion transaccionDetails) {
        Optional<Transaccion> updatedTransaccion = transaccionService.updateTransaccion(id, transaccionDetails);
        return updatedTransaccion.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar una transacción", description = "Elimina una transacción por su ID")
    public ResponseEntity<Void> deleteTransaccion(@PathVariable Long id) {
        if (transaccionService.deleteTransaccion(id)) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}