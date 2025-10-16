package com.upiiz.wc_ejercicio05_reuc.controller;

import com.upiiz.wc_ejercicio05_reuc.entity.Platillo;
import com.upiiz.wc_ejercicio05_reuc.service.PlatilloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
// ¡IMPORTANTE! La URL que uses en Postman debe ser EXACTAMENTE esta:
@RequestMapping("/garufa/public/v1/platillos")
public class PlatilloController {

    @Autowired
    private PlatilloService platilloService;

    // --- OBTENER TODOS los platillos ---
    @GetMapping
    public ResponseEntity<List<Platillo>> getAllPlatillos() {
        return ResponseEntity.ok(platilloService.getAllPlatillos());
    }

    // --- OBTENER UN platillo por su ID ---
    @GetMapping("/{id}")
    public ResponseEntity<Platillo> getPlatilloById(@PathVariable Long id) {
        return platilloService.getPlatilloById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // --- CREAR un nuevo platillo ---
    @PostMapping
    public ResponseEntity<Platillo> createPlatillo(@RequestBody Platillo platillo) {
        Platillo nuevoPlatillo = platilloService.saveOrUpdatePlatillo(platillo);
        return new ResponseEntity<>(nuevoPlatillo, HttpStatus.CREATED);
    }

    // --- ACTUALIZAR un platillo existente ---
    @PutMapping("/{id}")
    public ResponseEntity<Platillo> updatePlatillo(@PathVariable Long id, @RequestBody Platillo platilloDetails) {
        return platilloService.getPlatilloById(id)
                .map(platillo -> {
                    platillo.setNombre(platilloDetails.getNombre());
                    platillo.setDescripcion(platilloDetails.getDescripcion());
                    platillo.setPrecio(platilloDetails.getPrecio());
                    Platillo updatedPlatillo = platilloService.saveOrUpdatePlatillo(platillo);
                    return ResponseEntity.ok(updatedPlatillo);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    // --- ELIMINAR un platillo ---
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePlatillo(@PathVariable Long id) {
        return platilloService.getPlatilloById(id)
                .map(platillo -> {
                    platilloService.deletePlatillo(id);
                    return ResponseEntity.noContent().<Void>build(); // Código 204
                })
                .orElse(ResponseEntity.notFound().build()); // Código 404
    }
}