package com.consultoria.service;

import com.consultoria.model.Etapa;
import com.consultoria.model.Projeto;
import com.consultoria.repository.JsonRepository;

import java.time.LocalDate;
import java.util.*;

public class ProjetoService {
    private final JsonRepository<Projeto> repo;
    private final List<Projeto> projetos;
    private final EtapaService etapaService;

    public ProjetoService(JsonRepository<Projeto> repo, EtapaService etapaService) {
        this.repo = repo;
        this.projetos = repo.listAll();
        this.etapaService = etapaService;
    }

    public Projeto criarProjetoComEtapas(String id, String nome, String descricao, String clienteId, String consultorId, String contratoId) {
        Projeto projeto = new Projeto(id, nome, descricao, clienteId, consultorId, contratoId, "Em andamento");
        projetos.add(projeto);
        repo.saveAll(projetos);

        String idEtapa1 = java.util.UUID.randomUUID().toString();
        String idEtapa2 = java.util.UUID.randomUUID().toString();

        Etapa etapa1 = new Etapa(idEtapa1, id, "Análise Inicial", "Pendente", LocalDate.now().plusDays(7));
        Etapa etapa2 = new Etapa(idEtapa2, id, "Implementação", "Pendente", LocalDate.now().plusDays(20));

        etapaService.criarEtapa(etapa1);
        etapaService.criarEtapa(etapa2);

        projeto.adicionarEtapa(idEtapa1);
        projeto.adicionarEtapa(idEtapa2);
        repo.saveAll(projetos);

        return projeto;
    }

    public List<Projeto> listar() {
        return new ArrayList<>(projetos);
    }

    public Optional<Projeto> buscar(String id) {
        return projetos.stream().filter(p -> p.getId().equals(id)).findFirst();
    }

    public List<Projeto> buscarPorCliente(String clienteId) {
        return projetos.stream()
                .filter(p -> p.getClienteId().equals(clienteId))
                .toList();
    }
}