package com.consultoria.service;

import com.consultoria.model.Projeto;
import com.consultoria.repository.JsonRepository;

import java.util.List;
import java.util.Optional;

public class ProjetoService {

    private final JsonRepository<Projeto> repo;
    private final List<Projeto> projetos;

    public ProjetoService(JsonRepository<Projeto> repo) {
        this.repo = repo;
        this.projetos = repo.listAll();
    }

    public Projeto criarProjeto(Projeto p) {
        projetos.add(p);
        repo.saveAll(projetos);
        return p;
    }

    public List<Projeto> listar() {
        return projetos;
    }

    public Optional<Projeto> buscarPorId(String id) {
        return projetos.stream().filter(p -> p.getId().equals(id)).findFirst();
    }
}
