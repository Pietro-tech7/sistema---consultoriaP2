package com.consultoria.model;

import java.time.LocalDate;

public class Pagamento {
    private String id;
    private String etapaId;
    private String clienteId;
    private String projetoId;
    private String contratoId;
    private double valor;
    private LocalDate dataPagamento;
    private String status;

    public Pagamento() {}

    public Pagamento(String id, String etapaId, String clienteId, String projetoId, String contratoId, double valor) {
        this.id = id;
        this.etapaId = etapaId;
        this.clienteId = clienteId;
        this.projetoId = projetoId;
        this.contratoId = contratoId;
        this.valor = valor;
        this.status = "Pendente";
    }

    public String getId() { return id; }
    public String getEtapaId() { return etapaId; }
    public String getClienteId() { return clienteId; }
    public String getProjetoId() { return projetoId; }
    public String getContratoId() { return contratoId; }
    public double getValor() { return valor; }
    public String getStatus() { return status; }
    public LocalDate getDataPagamento() { return dataPagamento; }

    public void setClienteId(String clienteId) { this.clienteId = clienteId; }
    public void setProjetoId(String projetoId) { this.projetoId = projetoId; }
    public void setContratoId(String contratoId) { this.contratoId = contratoId; }
    public void setValor(double valor) { this.valor = valor; }
    public void setStatus(String status) { this.status = status; }

    public void registrarPagamento() {
        this.status = "Pago";
        this.dataPagamento = LocalDate.now();
    }

    @Override
    public String toString() {
        return "Pagamento " + id + " - Cliente: " + clienteId + " - Valor: " + valor + " - Status: " + status;
    }
}