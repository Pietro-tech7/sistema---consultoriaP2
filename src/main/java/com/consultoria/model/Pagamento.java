package com.consultoria.model;

import java.time.LocalDate;

public class Pagamento {
    private String id;
    private String etapaId;
    private double valor;
    private LocalDate dataPagamento;
    private String status;

    public Pagamento() {}

    public Pagamento(String id, String etapaId, double valor) {
        this.id = id;
        this.etapaId = etapaId;
        this.valor  = valor;
        this.status = "Pendente";
        this.dataPagamento = LocalDate.now();
    }

    public String getId() { return id; }
    public String getEtapaId() { return etapaId; }
    public double getValor() { return valor; }
    public void setValor(double valor) { this.valor = valor; }

    public String getStatus() { return status; }
    public LocalDate getDataPagamento() { return dataPagamento; }

    @Override
    public String toString() {
        return "Pagamento " + id + " etapa=" + etapaId + " valor=" + valor;
    }
}
