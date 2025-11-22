package com.consultoria.model;

public class Consultor extends Pessoa {
    private String especializacao;

    public Consultor() {}

    public Consultor(String id, String nome, String email, String especializacao) {
        super(id, nome, email);
        this.especializacao = especializacao;
    }
    public String getEspecializacao() { return especializacao; }
    public void setEspecializacao(String e) { this.especializacao = e; }

    @Override
    public String toString() {
        return super.toString() + " [" + especializacao + "]";
    }
}
