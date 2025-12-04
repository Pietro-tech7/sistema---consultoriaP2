package com.consultoria.service;

public class FidelidadeService {
    private final ClienteService clienteService;

    public FidelidadeService(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    public void registrarPontos(String clienteId, int pontos) {
        clienteService.adicionarPontos(clienteId, pontos);
    }
}