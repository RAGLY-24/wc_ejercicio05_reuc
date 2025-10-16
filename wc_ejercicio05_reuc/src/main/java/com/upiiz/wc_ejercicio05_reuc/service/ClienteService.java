package com.upiiz.wc_ejercicio05_reuc.service;

import com.upiiz.wc_ejercicio05_reuc.entity.Cliente;
import com.upiiz.wc_ejercicio05_reuc.repository.ClienteRepository; // Asegúrate que el nombre de tu repositorio es ClienteRepository
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public List<Cliente> getClientes() {
        return clienteRepository.findAll();
    }

    public Optional<Cliente> getCliente(Long id) {
        return clienteRepository.findById(id);
    }

    public Cliente saveOrUpdate(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    public void delete(Long id) {
        clienteRepository.deleteById(id);
    }
}