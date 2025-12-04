package com.consultoria.service;

import com.consultoria.model.Contrato;
import com.consultoria.repository.JsonRepository;

import java.util.*;
import java.util.stream.Collectors;

public class ContratoService {
    private final JsonRepository<Contrato> repo;
    private final List<Contrato> contratos;

    public ContratoService(JsonRepository<Contrato> repo) {
        this.repo = repo;
        this.contratos = repo.listAll();
    }

    public Contrato criar(Contrato c) {
        contratos.add(c);
        repo.saveAll(contratos);
        return c;
    }

    public List<Contrato> listar() {
        return new ArrayList<>(contratos);
    }

    public Optional<Contrato> buscar(String id) {
        return contratos.stream().filter(c -> c.getId().equals(id)).findFirst();
    }

    public List<Contrato> buscarPorCliente(String clienteId) {
        return contratos.stream()
                .filter(c -> c.getClienteId().equals(clienteId))
                .collect(Collectors.toList());
    }
}