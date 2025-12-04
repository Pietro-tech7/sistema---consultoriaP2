package com.consultoria.service;

import com.consultoria.model.Pagamento;
import com.consultoria.repository.JsonRepository;

import java.util.*;

public class PagamentoService {
    private final JsonRepository<Pagamento> repo;
    private final List<Pagamento> pagamentos;
    private final ClienteService clienteService;

    public PagamentoService(JsonRepository<Pagamento> repo, ClienteService clienteService) {
        this.repo = repo;
        this.pagamentos = repo.listAll();
        this.clienteService = clienteService;
    }

    public Pagamento registrar(Pagamento p, String clienteId) {
        var clienteOpt = clienteService.buscar(clienteId);
        if (clienteOpt.isEmpty()) {
            throw new RuntimeException("Cliente não encontrado.");
        }

        double valorFinal = clienteOpt.get().aplicarDesconto(p.getValor());
        p.setValor(valorFinal);
        p.setClienteId(clienteId); // ← AGORA FUNCIONA

        pagamentos.add(p);
        repo.saveAll(pagamentos);
        return p;
    }

    public void marcarComoPago(String id) {
        var opt = pagamentos.stream().filter(p -> p.getId().equals(id)).findFirst();
        if (opt.isPresent()) {
            opt.get().registrarPagamento();
            repo.saveAll(pagamentos);
        }
    }

    public List<Pagamento> listar() {
        return new ArrayList<>(pagamentos);
    }
}