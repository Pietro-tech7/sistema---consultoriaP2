package com.consultoria.service;

import com.consultoria.model.Consultor;
import com.consultoria.repository.JsonRepository;
import java.util.List;
import java.util.Optional;

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
        return consultores;
    }

    public Optional<Consultor> buscarPorId(String id) {
        return consultores.stream().filter(c -> c.getId().equals(id)).findFirst();
    }
}
