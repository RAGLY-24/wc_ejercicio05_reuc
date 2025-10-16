package com.upiiz.wc_ejercicio05_reuc.entity;

import com.fasterxml.jackson.annotation.JsonBackReference; // <-- AÑADE ESTE IMPORT
import jakarta.persistence.*;
import lombok.Data;
import java.math.BigDecimal;

@Data
@Entity
@Table(name = "detalle_pedidos")
public class DetallePedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonBackReference("pedido-detalle") // <-- AÑADE ESTA ANOTACIÓN
    @ManyToOne
    @JoinColumn(name = "pedido_id")
    private Pedido pedido;

    @ManyToOne
    @JoinColumn(name = "platillo_id")
    private Platillo platillo;

    @Column(name = "precio_actual")
    private BigDecimal precioActual;

    private int cantidad;
}