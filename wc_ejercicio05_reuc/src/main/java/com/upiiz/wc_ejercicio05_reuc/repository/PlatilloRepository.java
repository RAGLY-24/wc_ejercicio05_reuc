package com.upiiz.wc_ejercicio05_reuc.repository;

// AÑADE ESTA LÍNEA para importar tu entidad
import com.upiiz.wc_ejercicio05_reuc.entity.Platillo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlatilloRepository extends JpaRepository<Platillo, Long> {
    // Spring Data JPA crea automáticamente los métodos CRUD por ti.
    // ¡No necesitas escribir nada más aquí por ahora!
}