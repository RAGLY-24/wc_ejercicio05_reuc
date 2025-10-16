package com.upiiz.wc_ejercicio05_reuc.repository;


import com.upiiz.wc_ejercicio05_reuc.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
}
