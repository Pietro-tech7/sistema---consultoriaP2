package com.consultoria.service;

import com.consultoria.model.Cliente;
import com.consultoria.model.Projeto;

import java.util.List;

public class RelatorioService {
    private final ClienteService clienteService;
    private final ProjetoService projetoService;

    public RelatorioService(ClienteService clienteService, ProjetoService projetoService,
            /* outros serviços não usados aqui */ Object... ignore) {
        this.clienteService = clienteService;
        this.projetoService = projetoService;
    }

    public void gerarRelatorioGeral() {
        System.out.println("\n=== RELATÓRIO GERAL ===");
        List<Projeto> projetos = projetoService.listar();
        System.out.println("Total de projetos: " + projetos.size());
        for (Projeto p : projetos) {
            System.out.println("- " + p.getNome() + " (Cliente: " + p.getClienteId() + ")");
        }
    }

    public void gerarRelatorioVIP(String clienteId) {
        var clienteOpt = clienteService.buscar(clienteId);
        if (clienteOpt.isEmpty()) {
            System.out.println("Cliente não encontrado.");
            return;
        }

        Cliente cliente = clienteOpt.get();
        if (!"VIP".equalsIgnoreCase(cliente.getCategoria())) {
            System.out.println("Relatório VIP disponível apenas para clientes VIP.");
            return;
        }

        System.out.println("\n=== RELATÓRIO VIP - " + cliente.getNome() + " ===");
        System.out.println("Pontos de fidelidade: " + cliente.getPontosFidelidade());
        List<Projeto> projetos = projetoService.buscarPorCliente(clienteId);
        System.out.println("Projetos em andamento: " + projetos.size());
        for (Projeto p : projetos) {
            System.out.println("- " + p.getNome() + " | ID: " + p.getId());
        }
        System.out.println("\n[Insight exclusivo] Recomendamos um novo projeto de análise de dados com base em seu histórico.");
    }
}