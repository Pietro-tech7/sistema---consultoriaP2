package com.consultoria.model;

import java.util.ArrayList;
import java.util.List;

public class Cliente extends Pessoa {
    private String categoria;
    private int pontosFidelidade;
    private List<String> historicoContratoIds;

    public Cliente() {
        this.historicoContratoIds = new ArrayList<>();
    }

    public Cliente(String id, String nome, String email, String categoria) {
        super(id, nome, email);
        this.categoria = categoria;
        this.pontosFidelidade = 0;
        this.historicoContratoIds = new ArrayList<>();
    }

    public String getCategoria() { return categoria; }
    public int getPontosFidelidade() { return pontosFidelidade; }
    public void addPontos(int pontos) { this.pontosFidelidade += pontos; }
    public List<String> getHistoricoContratoIds() { return historicoContratoIds; }
    public void adicionarContratoAoHistorico(String contratoId) {
        historicoContratoIds.add(contratoId);
    }

    public double aplicarDesconto(double valor) {
        if ("VIP".equalsIgnoreCase(categoria)) {
            return valor * 0.90;
        }
        return valor;
    }

    @Override
    public String toString() {
        return super.toString() + " [" + categoria + "] pontos=" + pontosFidelidade;
    }
}