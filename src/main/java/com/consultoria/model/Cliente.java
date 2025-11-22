package com.consultoria.model;

public class Cliente extends Pessoa {
    protected String categoria;
    protected int pontosFidelidade;

    public Cliente() {}

    public Cliente(String id, String nome, String email, String categoria) {
        super(id, nome, email);
        this.categoria = categoria;
        this.pontosFidelidade = 0;
    }

    public String getCategoria() { return categoria; }
    public void setCategoria(String categoria) { this.categoria = categoria; }

    public int getPontosFidelidade() { return pontosFidelidade; }
    public void addPontos(int pontos) { this.pontosFidelidade += pontos; }

    public double aplicarDesconto(double valor) {
        return valor;
    }

        @Override
        public String toString() {
            return super.toString() + " [" + categoria + "] pontos=" + pontosFidelidade;

        }
    }
