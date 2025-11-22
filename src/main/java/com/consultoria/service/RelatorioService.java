package com.consultoria.service;


import com.consultoria.model.*;
import java.util.*;


public class RelatorioService {
    private final ClienteService clienteService;
    private final ConsultorService consultorService;
    private final ProjetoService projetoService;
    private final EtapaService etapaService;
    private final PagamentoService pagamentoService;


    public RelatorioService(ClienteService c, ConsultorService cs, ProjetoService ps, EtapaService es, PagamentoService pg) {
        this.clienteService = c;
        this.consultorService = cs;
        this.projetoService = ps;
        this.etapaService = es;
        this.pagamentoService = pg;
    }


    public void gerarRelatorioVIP() {
        System.out.println("===== RELATÓRIO VIP =====");


        for (Cliente c : clienteService.listar()) {
            if (!c.getCategoria().equalsIgnoreCase("VIP")) continue;


            System.out.println("\nCliente VIP: " + c.getNome());
            System.out.println("Pontos de fidelidade: " + c.getPontosFidelidade());

            // Filtra todos os projetos por cliente 'c', exibe seus detalhes e lista as etapas associadas a cada projeto
            projetoService.listar().stream()
                    .filter(p -> p.getClienteId().equals(c.getId()))
                    .forEach(p -> {
                        System.out.println("\nProjeto: " + p.getNome());
                        System.out.println("Descrição: " + p.getDescricao());
                        System.out.println("Status: " + p.getStatus());


                        etapaService.listar().stream()
                                .filter(e -> e.getId().contains(p.getId()))
                                .forEach(e -> System.out.println(" - Etapa: " + e));
                    });
        }
    }
}




