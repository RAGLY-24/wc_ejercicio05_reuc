package com.upiiz.wc_ejercicio05_reuc.repository;

// ¡AÑADE ESTA LÍNEA PARA SOLUCIONAR EL ERROR!
import com.upiiz.wc_ejercicio05_reuc.entity.Pedido;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long> {
    // Spring Data JPA crea los métodos por ti, no necesitas añadir nada más.
}