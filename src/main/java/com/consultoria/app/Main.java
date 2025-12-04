package com.consultoria.app;

import com.consultoria.model.*;
import com.consultoria.repository.JsonRepository;
import com.consultoria.service.*;
import com.google.gson.reflect.TypeToken;

import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);

    private static ClienteService clienteService;
    private static ConsultorService consultorService;
    private static ContratoService contratoService;
    private static ProjetoService projetoService;
    private static EtapaService etapaService;
    private static PagamentoService pagamentoService;
    private static FidelidadeService fidelidadeService;
    private static RelatorioService relatorioService;

    public static void main(String[] args) {
        inicializarServicos();

        boolean rodando = true;
        while (rodando) {
            System.out.println("\n=== SISTEMA DE GESTÃO - PLANILHA CERTA ===");
            System.out.println("1. Clientes");
            System.out.println("2. Consultores");
            System.out.println("3. Contratos");
            System.out.println("4. Projetos e Etapas");
            System.out.println("5. Pagamentos");
            System.out.println("6. Relatórios");
            System.out.println("7. Fidelidade");
            System.out.println("0. Sair");
            System.out.print("Opção: ");

            int op;
            try {
                op = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Opção inválida. Digite um número.");
                continue;
            }

            switch (op) {
                case 1 -> menuClientes();
                case 2 -> menuConsultores();
                case 3 -> menuContratos();
                case 4 -> menuProjetos();
                case 5 -> menuPagamentos();
                case 6 -> menuRelatorios();
                case 7 -> menuFidelidade();
                case 0 -> rodando = false;
                default -> System.out.println("Opção inválida.");
            }
        }
        System.out.println("Sistema encerrado.");
    }

    private static void inicializarServicos() {
        // Definir os tipos  Gson
        var tipoCliente = new TypeToken<java.util.List<Cliente>>(){}.getType();
        var tipoConsultor = new TypeToken<java.util.List<Consultor>>(){}.getType();
        var tipoContrato = new TypeToken<java.util.List<Contrato>>(){}.getType();
        var tipoProjeto = new TypeToken<java.util.List<Projeto>>(){}.getType();
        var tipoEtapa = new TypeToken<java.util.List<Etapa>>(){}.getType();
        var tipoPagamento = new TypeToken<java.util.List<Pagamento>>(){}.getType();

        // Criar repositórios
        JsonRepository<Cliente> repoCliente = new JsonRepository<>("data/clientes.json", tipoCliente);
        JsonRepository<Consultor> repoConsultor = new JsonRepository<>("data/consultores.json", tipoConsultor);
        JsonRepository<Contrato> repoContrato = new JsonRepository<>("data/contratos.json", tipoContrato);
        JsonRepository<Projeto> repoProjeto = new JsonRepository<>("data/projetos.json", tipoProjeto);
        JsonRepository<Etapa> repoEtapa = new JsonRepository<>("data/etapas.json", tipoEtapa);
        JsonRepository<Pagamento> repoPagamento = new JsonRepository<>("data/pagamentos.json", tipoPagamento);

        etapaService = new EtapaService(repoEtapa);
        clienteService = new ClienteService(repoCliente);
        consultorService = new ConsultorService(repoConsultor);
        contratoService = new ContratoService(repoContrato);
        projetoService = new ProjetoService(repoProjeto, etapaService);
        pagamentoService = new PagamentoService(repoPagamento, clienteService);
        fidelidadeService = new FidelidadeService(clienteService);
        relatorioService = new RelatorioService(clienteService, projetoService);
    }

    // MENUS
    private static void menuClientes() {
        System.out.println("\n--- Clientes ---");
        System.out.println("1. Cadastrar Regular\n2. Cadastrar VIP\n3. Listar\n0. Voltar");
        int op = lerOpcao();
        if (op == 1) {
            System.out.print("Nome: "); String n = scanner.nextLine();
            System.out.print("Email: "); String e = scanner.nextLine();
            var c = new Cliente(java.util.UUID.randomUUID().toString(), n, e, "Regular");
            clienteService.cadastrar(c);
            System.out.println("Cliente Regular cadastrado.");
        } else if (op == 2) {
            System.out.print("Nome: "); String n = scanner.nextLine();
            System.out.print("Email: "); String e = scanner.nextLine();
            clienteService.cadastrarVIP(java.util.UUID.randomUUID().toString(), n, e);
            System.out.println("Cliente VIP cadastrado.");
        } else if (op == 3) {
            clienteService.listar().forEach(System.out::println);
        }
    }

    private static void menuConsultores() {
        System.out.println("\n--- Consultores ---");
        System.out.println("1. Cadastrar\n2. Listar\n3. Buscar por especialização\n0. Voltar");
        int op = lerOpcao();
        if (op == 1) {
            System.out.print("Nome: "); String n = scanner.nextLine();
            System.out.print("Email: "); String e = scanner.nextLine();
            System.out.print("Especialização: "); String esp = scanner.nextLine();
            var c = new Consultor(java.util.UUID.randomUUID().toString(), n, e, esp);
            consultorService.cadastrar(c);
            System.out.println("Consultor cadastrado.");
        } else if (op == 2) {
            consultorService.listar().forEach(System.out::println);
        } else if (op == 3) {
            System.out.print("Especialização: "); String esp = scanner.nextLine();
            consultorService.buscarPorEspecializacao(esp).forEach(System.out::println);
        }
    }

    private static void menuContratos() {
        System.out.println("\n--- Contratos ---");
        System.out.println("1. Criar\n2. Listar\n0. Voltar");
        int op = lerOpcao();
        if (op == 1) {
            System.out.print("ID Cliente: "); String cli = scanner.nextLine();
            System.out.print("ID Consultor: "); String con = scanner.nextLine();
            System.out.print("Valor: "); double v = Double.parseDouble(scanner.nextLine());
            var clienteOpt = clienteService.buscar(cli);
            if (clienteOpt.isEmpty()) {
                System.out.println("Cliente não encontrado.");
                return;
            }
            String clausulas = clienteOpt.get().getCategoria().equals("VIP") ?
                    "Atendimento prioritário (VIP)" : "Contrato padrão";
            var c = new Contrato(
                    java.util.UUID.randomUUID().toString(),
                    cli, con, v,
                    java.time.LocalDate.now(),
                    java.time.LocalDate.now().plusMonths(3),
                    clausulas
            );
            contratoService.criar(c);
            clienteService.adicionarContratoAoHistorico(cli, c.getId());
            System.out.println("Contrato criado e vinculado ao cliente.");
        } else if (op == 2) {
            contratoService.listar().forEach(System.out::println);
        }
    }

    private static void menuProjetos() {
        System.out.println("\n--- Projetos e Etapas ---");
        System.out.println("1. Criar Projeto com Etapas\n2. Listar\n3. Atualizar Etapa\n0. Voltar");
        int op = lerOpcao();
        if (op == 1) {
            System.out.print("Nome: "); String nome = scanner.nextLine();
            System.out.print("ID Cliente: "); String cli = scanner.nextLine();
            System.out.print("ID Consultor: "); String con = scanner.nextLine();
            System.out.print("ID Contrato: "); String ct = scanner.nextLine();
            var p = projetoService.criarProjetoComEtapas(
                    java.util.UUID.randomUUID().toString(),
                    nome, "Descrição padrão",
                    cli, con, ct
            );
            System.out.println("Projeto e etapas criados (ID: " + p.getId() + ").");
        } else if (op == 2) {
            projetoService.listar().forEach(System.out::println);
        } else if (op == 3) {
            System.out.print("ID Etapa: "); String id = scanner.nextLine();
            System.out.print("Novo status: "); String status = scanner.nextLine();
            etapaService.atualizarStatus(id, status);
            System.out.println("Status da etapa atualizado.");
        }
    }

    private static void menuPagamentos() {
        System.out.println("\n--- Pagamentos ---");
        System.out.println("1. Registrar\n2. Marcar como Pago\n3. Listar\n0. Voltar");
        int op = lerOpcao();
        if (op == 1) {
            System.out.print("ID Etapa: "); String et = scanner.nextLine();
            System.out.print("ID Cliente: "); String cli = scanner.nextLine();
            System.out.print("Valor: "); double v = Double.parseDouble(scanner.nextLine());
            var pg = new Pagamento(
                    java.util.UUID.randomUUID().toString(),
                    et, cli, "", "", v
            );
            pagamentoService.registrar(pg, cli);
            System.out.println("Pagamento registrado com desconto (se VIP).");
        } else if (op == 2) {
            System.out.print("ID Pagamento: "); String id = scanner.nextLine();
            pagamentoService.marcarComoPago(id);
            System.out.println("Pagamento confirmado.");
        } else if (op == 3) {
            pagamentoService.listar().forEach(System.out::println);
        }
    }

    private static void menuRelatorios() {
        System.out.println("\n--- Relatórios ---");
        System.out.println("1. Geral\n2. VIP\n0. Voltar");
        int op = lerOpcao();
        if (op == 1) {
            relatorioService.gerarRelatorioGeral();
        } else if (op == 2) {
            System.out.print("ID Cliente VIP: "); String id = scanner.nextLine();
            relatorioService.gerarRelatorioVIP(id);
        }
    }

    private static void menuFidelidade() {
        System.out.print("ID Cliente: "); String id = scanner.nextLine();
        System.out.print("Pontos a adicionar: "); int p = Integer.parseInt(scanner.nextLine());
        fidelidadeService.registrarPontos(id, p);
        System.out.println("Pontos adicionados com sucesso.");
    }

    private static int lerOpcao() {
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            return -1;
        }
    }
}