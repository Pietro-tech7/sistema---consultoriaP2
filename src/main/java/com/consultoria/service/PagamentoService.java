package com.consultoria.service;

import com.consultoria.model.Cliente;
import com.consultoria.model.Pagamento;
import com.consultoria.repository.JsonRepository;

import java.util.List;

public class PagamentoService {

    private final JsonRepository<Pagamento> repo;
    private final List<Pagamento> pagamentos;

    public PagamentoService(JsonRepository<Pagamento> repo) {
        this.repo = repo;
        this.pagamentos = repo.listAll();
    }

    public Pagamento registrar(Pagamento p, Cliente cliente) {

        // aplica desconto VIP ou regular
        double valorFinal = cliente.aplicarDesconto(p.getValor());

        // atualiza o valor do pagamento via reflexão
        try {
            var campoValor = p.getClass().getDeclaredField("valor");
            campoValor.setAccessible(true);
            campoValor.set(p, valorFinal);
        } catch (Exception ignored) {}

        pagamentos.add(p);
        repo.saveAll(pagamentos);
        return p;
    }

    public void marcarComoPago(String id) {
        pagamentos.stream()
                .filter(pg -> pg.getId().equals(id))
                .findFirst()
                .ifPresent(pg -> {
                    try {
                        var campo = pg.getClass().getDeclaredField("status");
                        campo.setAccessible(true);
                        campo.set(pg, "Pago");
                    } catch (Exception ignored) {}
                    repo.saveAll(pagamentos);
                });
    }

    public List<Pagamento> listar() {
        return pagamentos;
    }
}
