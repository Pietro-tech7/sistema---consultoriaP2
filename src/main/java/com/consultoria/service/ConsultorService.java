package com.consultoria.service;

import com.consultoria.model.Consultor;
import com.consultoria.repository.JsonRepository;

import java.util.*;
import java.util.stream.Collectors;

public class ConsultorService {
    private final JsonRepository<Consultor> repo;
    private final List<Consultor> consultores;

    public ConsultorService(JsonRepository<Consultor> repo) {
        this.repo = repo;
        this.consultores = repo.listAll();
    }

    public Consultor cadastrar(Consultor c) {
        consultores.add(c);
        repo.saveAll(consultores);
        return c;
    }

    public List<Consultor> listar() {
        return new ArrayList<>(consultores);
    }

    public Optional<Consultor> buscar(String id) {
        return consultores.stream().filter(c -> c.getId().equals(id)).findFirst();
    }

    public List<Consultor> buscarPorEspecializacao(String especializacao) {
        return consultores.stream()
                .filter(c -> c.getEspecializacao().equalsIgnoreCase(especializacao))
                .collect(Collectors.toList());
    }
}