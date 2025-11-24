package com.consultoria.app;

import com.consultoria.model.*;
import com.consultoria.repository.JsonRepository;
import com.consultoria.service.*;
import com.google.gson.reflect.TypeToken;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);

        // Repositórios com tratamento correto para LocalDate
        JsonRepository<Cliente> repoCliente =
                new JsonRepository<>("data/clientes.json", new TypeToken<List<Cliente>>(){}.getType());

        JsonRepository<Consultor> repoConsultor =
                new JsonRepository<>("data/consultores.json", new TypeToken<List<Consultor>>(){}.getType());

        JsonRepository<Contrato> repoContrato =
                new JsonRepository<>("data/contratos.json", new TypeToken<List<Contrato>>(){}.getType());

        JsonRepository<Projeto> repoProjeto =
                new JsonRepository<>("data/projetos.json", new TypeToken<List<Projeto>>(){}.getType());

        JsonRepository<Etapa> repoEtapa =
                new JsonRepository<>("data/etapas.json", new TypeToken<List<Etapa>>(){}.getType());

        JsonRepository<Pagamento> repoPagamento =
                new JsonRepository<>("data/pagamentos.json", new TypeToken<List<Pagamento>>(){}.getType());

        // Servicos
        ClienteService clienteService = new ClienteService(repoCliente);
        ConsultorService consultorService = new ConsultorService(repoConsultor);
        ContratoService contratoService = new ContratoService(repoContrato);
        ProjetoService projetoService = new ProjetoService(repoProjeto);
        EtapaService etapaService = new EtapaService(repoEtapa);
        PagamentoService pagamentoService = new PagamentoService(repoPagamento);

        RelatorioService relatorioService =
                new RelatorioService(clienteService, consultorService, projetoService, etapaService, pagamentoService);

        FidelidadeService fidelidadeService =
                new FidelidadeService(clienteService);

        System.out.println("Sistema carregado!");
    }
}
