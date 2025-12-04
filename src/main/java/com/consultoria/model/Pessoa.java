package com.consultoria.model;

public abstract class Pessoa {
    protected String id;
    protected String nome;
    protected String email;

    public Pessoa() {}

    public Pessoa(String id, String nome, String email) {
        this.id = id;
        this.nome = nome;
        this.email = email;
    }

    public String getId() { return id; }
    public String getNome() { return nome; }
    public String getEmail() { return email; }

    @Override
    public String toString() {
        return nome + " (" + email + ")";
    }
}