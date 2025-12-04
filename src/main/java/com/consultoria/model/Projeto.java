package com.consultoria.model;

import java.util.ArrayList;
import java.util.List;

public class Projeto {
    private String id;
    private String nome;
    private String descricao;
    private String clienteId;
    private String consultorId;
    private String contratoId; // valor vem do contrato
    private String status;
    private List<String> etapaIds;

    public Projeto() {
        this.etapaIds = new ArrayList<>();
    }

    public Projeto(String id, String nome, String descricao, String clienteId, String consultorId, String contratoId, String status) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.clienteId = clienteId;
        this.consultorId = consultorId;
        this.contratoId = contratoId;
        this.status = status;
        this.etapaIds = new ArrayList<>();
    }

    public String getId() { return id; }
    public String getNome() { return nome; }
    public String getDescricao() { return descricao; }
    public String getClienteId() { return clienteId; }
    public String getConsultorId() { return consultorId; }
    public String getContratoId() { return contratoId; }
    public String getStatus() { return status; }
    public List<String> getEtapaIds() { return etapaIds; }
    public void adicionarEtapa(String etapaId) { etapaIds.add(etapaId); }

    @Override
    public String toString() {
        return "\n[PROJETO] " + nome +
                "\nID: " + id +
                "\nContrato: " + contratoId +
                "\nCliente: " + clienteId +
                "\nStatus: " + status;
    }
}