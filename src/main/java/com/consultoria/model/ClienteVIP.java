package com.consultoria.model;

public class ClienteVIP extends Cliente {
    public ClienteVIP() {
        super();
    }

    public ClienteVIP(String id, String nome, String email) {
        super(id, nome, email, "VIP");
    }
}