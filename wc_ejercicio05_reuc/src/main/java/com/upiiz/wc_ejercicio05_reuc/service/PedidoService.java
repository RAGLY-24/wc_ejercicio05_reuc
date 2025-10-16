package com.upiiz.wc_ejercicio05_reuc.service;

import com.upiiz.wc_ejercicio05_reuc.entity.DetallePedido;
import com.upiiz.wc_ejercicio05_reuc.entity.Pedido;
import com.upiiz.wc_ejercicio05_reuc.entity.Platillo;
import com.upiiz.wc_ejercicio05_reuc.repository.DetallePedidoRepository;
import com.upiiz.wc_ejercicio05_reuc.repository.PedidoRepository;
import com.upiiz.wc_ejercicio05_reuc.repository.PlatilloRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository; // <-- La variable a usar

    @Autowired
    private PlatilloRepository platilloRepository; // <-- La variable a usar

    @Autowired
    private DetallePedidoRepository detallePedidoRepository; // <-- La variable a usar

    // --- Métodos CRUD básicos para Pedidos ---

    public List<Pedido> getAllPedidos() {
        return pedidoRepository.findAll(); // CORREGIDO
    }

    public Optional<Pedido> getPedidoById(Long id) {
        return pedidoRepository.findById(id); // CORREGIDO
    }

    public Pedido saveOrUpdatePedido(Pedido pedido) {
        return pedidoRepository.save(pedido); // CORREGIDO
    }

    public void deletePedido(Long id) {
        pedidoRepository.deleteById(id);
    }

    // --- Lógica para la relación Pedido <-> Platillo ---

    @Transactional
    public Pedido agregarPlatilloAPedido(Long pedidoId, Long platilloId, int cantidad) {
        Pedido pedido = pedidoRepository.findById(pedidoId)
                .orElseThrow(() -> new RuntimeException("Pedido no encontrado"));
        Platillo platillo = platilloRepository.findById(platilloId)
                .orElseThrow(() -> new RuntimeException("Platillo no encontrado"));

        DetallePedido detalle = new DetallePedido();
        detalle.setPedido(pedido);
        detalle.setPlatillo(platillo);
        detalle.setCantidad(cantidad);
        detalle.setPrecioActual(platillo.getPrecio());

        detallePedidoRepository.save(detalle); // CORREGIDO

        actualizarTotalPedido(pedido);
        return pedido;
    }

    @Transactional
    public Pedido quitarPlatilloDePedido(Long pedidoId, Long platilloId) {
        Pedido pedido = pedidoRepository.findById(pedidoId) // CORREGIDO
                .orElseThrow(() -> new RuntimeException("Pedido no encontrado"));

        DetallePedido detalle = detallePedidoRepository.findByPedidoIdAndPlatilloId(pedidoId, platilloId) // CORREGIDO
                .orElseThrow(() -> new RuntimeException("El platillo no está en el pedido"));

        detallePedidoRepository.delete(detalle); // CORREGIDO

        actualizarTotalPedido(pedido);
        return pedido;
    }

    private void actualizarTotalPedido(Pedido pedido) {
        BigDecimal total = BigDecimal.ZERO;
        // Refresca la lista de detalles desde la base de datos para asegurar consistencia
        List<DetallePedido> detalles = detallePedidoRepository.findAllByPedidoId(pedido.getId());

        for (DetallePedido item : detalles) {
            BigDecimal subtotal = item.getPrecioActual().multiply(new BigDecimal(item.getCantidad()));
            total = total.add(subtotal);
        }

        pedido.setTotal(total);
        pedidoRepository.save(pedido); // CORREGIDO
    }
}