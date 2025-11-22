package com.consultoria.model;

public class ClienteVIP  extends Cliente {

    public ClienteVIP() {}

    public ClienteVIP(String id, String nome, String email) {
        super(id, nome, email, "VIP");
    }

    @Override
    public double aplicarDesconto(double valor) {
        return valor * 0.90; // 10% de desconto
    }
}
