package com.consultoria.model;

import java.io.Serializable;

public class Projeto implements Serializable {
    private String id;
    private String nome;
    private String descricao;
    private String clienteId;
    private String consultorId;
    private double valor;
    private String status;

    public Projeto(String id, String nome, String descricao, String clienteId, String consultorId, double valor, String status) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.clienteId = clienteId;
        this.consultorId = consultorId;
        this.valor = valor;
        this.status = status;
    }

    public String getId() { return id; }
    public String getNome() { return nome; }
    public String getDescricao() { return descricao; }
    public String getClienteId() { return clienteId; }
    public String getConsultorId() { return consultorId; }
    public double getValor() { return valor; }
    public String getStatus() { return status; }

    @Override
    public String toString() {
        return "\n[PROJETO] " + nome +
                "\nID: " + id +
                "\nDescrição: " + descricao +
                "\nCliente ID: " + clienteId +
                "\nConsultor ID: " + consultorId +
                "\nValor: R$ " + valor +
                "\nStatus: " + status;
    }
}