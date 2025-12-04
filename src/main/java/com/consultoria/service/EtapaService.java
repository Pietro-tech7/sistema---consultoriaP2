package com.consultoria.service;

import com.consultoria.model.Etapa;
import com.consultoria.repository.JsonRepository;

import java.util.*;
import java.util.stream.Collectors;

public class EtapaService {
    private final JsonRepository<Etapa> repo;
    private final List<Etapa> etapas;

    public EtapaService(JsonRepository<Etapa> repo) {
        this.repo = repo;
        this.etapas = repo.listAll();
    }

    public Etapa criarEtapa(Etapa e) {
        etapas.add(e);
        repo.saveAll(etapas);
        return e;
    }

    public List<Etapa> listar() {
        return new ArrayList<>(etapas);
    }

    public Optional<Etapa> buscar(String id) {
        return etapas.stream().filter(e -> e.getId().equals(id)).findFirst();
    }

    public List<Etapa> buscarPorProjeto(String projetoId) {
        return etapas.stream()
                .filter(e -> e.getProjetoId().equals(projetoId))
                .collect(Collectors.toList());
    }

    public void atualizarStatus(String etapaId, String novoStatus) {
        buscar(etapaId).ifPresent(etapa -> {
            etapa.setStatus(novoStatus);
            repo.saveAll(etapas);
        });
    }
}