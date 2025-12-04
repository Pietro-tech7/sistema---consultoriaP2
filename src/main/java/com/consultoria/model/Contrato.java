package com.consultoria.model;

import java.time.LocalDate;

public class Contrato {
    private String id;
    private String clienteId;
    private String consultorId;
    private double valorTotal;
    private LocalDate dataInicio;
    private LocalDate dataFim;
    private String clausulas;

    public Contrato() {}

    public Contrato(String id, String clienteId, String consultorId, double valorTotal, LocalDate dataInicio, LocalDate dataFim, String clausulas) {
        this.id = id;
        this.clienteId = clienteId;
        this.consultorId = consultorId;
        this.valorTotal = valorTotal;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        this.clausulas = clausulas;
    }

    public String getId() { return id; }
    public String getClienteId() { return clienteId; }
    public String getConsultorId() { return consultorId; }
    public double getValorTotal() { return valorTotal; }

    @Override
    public String toString() {
        return id + " - contrato de " + clienteId + " com " + consultorId + " valor= " + valorTotal;
    }
}