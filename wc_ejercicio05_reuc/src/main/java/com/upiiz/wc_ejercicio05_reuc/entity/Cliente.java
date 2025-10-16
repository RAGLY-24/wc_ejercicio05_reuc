package com.upiiz.wc_ejercicio05_reuc.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference; // <-- AÑADE ESTE IMPORT
import jakarta.persistence.*;
import lombok.Data;
import java.util.List;

@Data
@Entity
@Table(name = "clientes")
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String email;
    private String telefono;

    @JsonManagedReference // <-- AÑADE ESTA ANOTACIÓN
    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Pedido> pedidos;
}