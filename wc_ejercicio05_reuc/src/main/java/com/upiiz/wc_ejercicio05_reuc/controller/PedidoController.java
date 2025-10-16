package com.upiiz.wc_ejercicio05_reuc.controller;

import com.upiiz.wc_ejercicio05_reuc.entity.Pedido;
import com.upiiz.wc_ejercicio05_reuc.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/pedidos")
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    // --- Métodos CRUD para Pedidos ---
    @GetMapping
    public ResponseEntity<List<Pedido>> getAllPedidos() {
        return ResponseEntity.ok(pedidoService.getAllPedidos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pedido> getPedidoById(@PathVariable Long id) {
        return pedidoService.getPedidoById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Pedido> createPedido(@RequestBody Pedido pedido) {
        Pedido nuevoPedido = pedidoService.saveOrUpdatePedido(pedido);
        return new ResponseEntity<>(nuevoPedido, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePedido(@PathVariable Long id) {
        return pedidoService.getPedidoById(id)
                .map(p -> {
                    pedidoService.deletePedido(id);
                    return ResponseEntity.noContent().<Void>build();
                })
                .orElse(ResponseEntity.notFound().build());
    }

    // --- Métodos para la relación Pedido <-> Platillo ---
    @GetMapping("/{idPedido}/platillos")
    public ResponseEntity<?> listarPlatillosDePedido(@PathVariable Long idPedido) {
        return pedidoService.getPedidoById(idPedido)
                .map(pedido -> ResponseEntity.ok(pedido.getDetalles()))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/{idPedido}/platillos/{idPlatillo}")
    public ResponseEntity<?> agregarPlatilloAPedido(@PathVariable Long idPedido, @PathVariable Long idPlatillo, @RequestParam int cantidad) {
        try {
            Pedido pedidoActualizado = pedidoService.agregarPlatilloAPedido(idPedido, idPlatillo, cantidad);
            return ResponseEntity.ok(pedidoActualizado);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @DeleteMapping("/{idPedido}/platillos/{idPlatillo}")
    public ResponseEntity<?> quitarPlatilloDePedido(@PathVariable Long idPedido, @PathVariable Long idPlatillo) {
        try {
            Pedido pedidoActualizado = pedidoService.quitarPlatilloDePedido(idPedido, idPlatillo);
            return ResponseEntity.ok(pedidoActualizado);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}