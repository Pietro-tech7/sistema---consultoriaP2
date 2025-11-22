package com.consultoria.service;


import com.consultoria.model.Cliente;


public class FidelidadeService {
    private final ClienteService clienteService;


    public FidelidadeService(ClienteService clienteService) {
        this.clienteService = clienteService;
    }


    public void registrarPontos(Cliente c, int pontos) {
        c.addPontos(pontos);
        clienteService.salvar();
    }
}