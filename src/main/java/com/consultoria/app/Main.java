package com.consultoria.app;

import com.consultoria.model.*;
import com.consultoria.repository.JsonRepository;
import com.consultoria.service.*;
import com.google.gson.reflect.TypeToken;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public class Main {
    public static void main(String[] args) {

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

        ClienteService clienteService = new ClienteService(repoCliente);
        ConsultorService consultorService = new ConsultorService(repoConsultor);
        ContratoService contratoService = new ContratoService(repoContrato);
        ProjetoService projetoService = new ProjetoService(repoProjeto);
        EtapaService etapaService = new EtapaService(repoEtapa);
        PagamentoService pagamentoService = new PagamentoService(repoPagamento);

        FidelidadeService fidelidadeService = new FidelidadeService(clienteService);
        RelatorioService relatorioService = new RelatorioService(
                clienteService, consultorService, projetoService, etapaService, pagamentoService
        );
        Cliente vip = clienteService.cadastrarVIP(
                UUID.randomUUID().toString(),
                "Rute Elias",
                "rute@empresa.com"
        );

        Consultor consultor = consultorService.cadastrar(
                new Consultor(UUID.randomUUID().toString(), "João Consultor", "joao@consult.com", "Financeiro")
        );

        Contrato contrato = contratoService.criar(
                new Contrato(
                        UUID.randomUUID().toString(),
                        vip.getId(),
                        consultor.getId(),
                        5000.00,
                        LocalDate.now(),
                        LocalDate.now().plusMonths(3),
                        "Atendimento prioritário (VIP)"
                )
        );

        Projeto projeto = projetoService.criarProjeto(
                new Projeto(
                        UUID.randomUUID().toString(),
                        "Consultoria Financeira",
                        "Projeto completo de análise e planejamento",
                        vip.getId(),
                        consultor.getId(),
                        5000.00,
                        "Em andamento"
                )
        );
        Etapa etapa1 = etapaService.criarEtapa(
                new Etapa(UUID.randomUUID().toString(), "Análise Inicial", "Pendente", LocalDate.now().plusDays(7))
        );

        Etapa etapa2 = etapaService.criarEtapa(
                new Etapa(UUID.randomUUID().toString(), "Implementação", "Pendente", LocalDate.now().plusDays(20))
        );

        Pagamento pagamento = pagamentoService.registrar(
                new Pagamento(UUID.randomUUID().toString(), etapa1.getId(), 2000.00),
                vip
        );

        pagamentoService.marcarComoPago(pagamento.getId());

        fidelidadeService.registrarPontos(vip, 50);

        relatorioService.gerarRelatorioVIP();

        System.out.println("\n=== Sistema finalizado com sucesso! ===");
    }
}
