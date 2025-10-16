package com.upiiz.wc_ejercicio05_reuc.controller;

// CORRECCIÓN: Apunta a las clases Cliente y ClienteService de TU proyecto.
import com.upiiz.wc_ejercicio05_reuc.entity.Cliente;
import com.upiiz.wc_ejercicio05_reuc.service.ClienteService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/clientes") // Sugerencia: una ruta más corta y estándar
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @GetMapping
    public ResponseEntity<List<Cliente>> getAllClientes() {
        return ResponseEntity.ok(clienteService.getClientes());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cliente> getClienteById(@PathVariable Long id) {
        return clienteService.getCliente(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Cliente> createCliente(@RequestBody Cliente cliente) {
        Cliente nuevoCliente = clienteService.saveOrUpdate(cliente);
        return new ResponseEntity<>(nuevoCliente, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cliente> updateCliente(@PathVariable Long id, @RequestBody Cliente clienteDetails) {
        return clienteService.getCliente(id)
                .map(cliente -> {
                    cliente.setNombre(clienteDetails.getNombre());
                    cliente.setEmail(clienteDetails.getEmail());
                    cliente.setTelefono(clienteDetails.getTelefono());
                    Cliente updatedCliente = clienteService.saveOrUpdate(cliente);
                    return ResponseEntity.ok(updatedCliente);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCliente(@PathVariable Long id) {
        // Esta es una forma un poco más limpia de escribir la misma lógica
        return clienteService.getCliente(id)
                .map(cliente -> {
                    clienteService.delete(id);
                    return ResponseEntity.noContent().<Void>build(); // 204
                })
                .orElse(ResponseEntity.notFound().build()); // 404
    }
}