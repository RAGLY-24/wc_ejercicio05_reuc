package com.upiiz.wc_ejercicio05_reuc.repository;

// 1. ¡AÑADE ESTA LÍNEA PARA IMPORTAR LA ENTIDAD!
import com.upiiz.wc_ejercicio05_reuc.entity.DetallePedido;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;
import java.util.List; // Importa List para el nuevo método

@Repository
// 2. ¡CORRIGE EL NOMBRE DE LA CLASE AQUÍ! (Debe ser DetallePedido)
public interface DetallePedidoRepository extends JpaRepository<DetallePedido, Long> {

    // Este método es el que usas en PedidoService para buscar un detalle específico
    Optional<DetallePedido> findByPedidoIdAndPlatilloId(Long pedidoId, Long platilloId);

    // Este método es útil para recalcular el total en PedidoService
    List<DetallePedido> findAllByPedidoId(Long pedidoId);
}