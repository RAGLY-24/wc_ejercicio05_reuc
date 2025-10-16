package com.upiiz.wc_ejercicio05_reuc.entity;

import com.fasterxml.jackson.annotation.JsonBackReference; // <-- AÑADE ESTE IMPORT
import com.fasterxml.jackson.annotation.JsonManagedReference; // <-- AÑADE ESTE IMPORT TAMBIÉN PARA LA OTRA RELACIÓN
import jakarta.persistence.*;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Data
@Entity
@Table(name = "pedidos")
public class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate fecha;
    private BigDecimal total;

    @JsonBackReference // <-- AÑADE ESTA ANOTACIÓN AQUÍ
    @ManyToOne
    @JoinColumn(name = "cliente_id", nullable = false)
    private Cliente cliente;

    // Aprovechando, puedes manejar la relación con DetallePedido de la misma forma
    @JsonManagedReference("pedido-detalle") // Usamos un nombre para diferenciarla de la otra relación
    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DetallePedido> detalles;
}