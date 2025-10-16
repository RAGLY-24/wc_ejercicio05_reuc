package com.upiiz.wc_ejercicio05_reuc.service;

import com.upiiz.wc_ejercicio05_reuc.entity.Platillo;
import com.upiiz.wc_ejercicio05_reuc.repository.PlatilloRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PlatilloService {

    @Autowired
    private PlatilloRepository platilloRepository;

    public List<Platillo> getAllPlatillos() {
        return platilloRepository.findAll();
    }

    public Optional<Platillo> getPlatilloById(Long id) {
        return platilloRepository.findById(id);
    }

    public Platillo saveOrUpdatePlatillo(Platillo platillo) {
        return platilloRepository.save(platillo);
    }

    public void deletePlatillo(Long id) {
        platilloRepository.deleteById(id);
    }
}