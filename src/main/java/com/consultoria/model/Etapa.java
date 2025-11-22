package com.consultoria.model;

import java.time.LocalDate;

public class Etapa {
    private String id;
    private String nome;
    private String status;
    private LocalDate prazo;

    public Etapa() {}

    public Etapa(String id, String nome, String status, LocalDate prazo) {
        this.id = id;
        this.nome = nome;
        this.status = status; // corrigido
        this.prazo = prazo;
    }

    public String getId() { return id; }
    public String getNome() { return nome; }
    public String getStatus() { return status; }
    public LocalDate getPrazo() { return prazo; }

    public void setStatus(String status) { this.status = status; }

    @Override
    public String toString() {
        return nome + " [" + status + "] prazo=" + prazo;
    }
}
