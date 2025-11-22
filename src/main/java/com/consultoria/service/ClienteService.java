package com.consultoria.service;

import com.consultoria.model.Cliente;
import com.consultoria.model.ClienteVIP;
import com.consultoria.repository.JsonRepository;

import java.util.*;

public class ClienteService {

    private final JsonRepository<Cliente> repo;
    private final List<Cliente> clientes;

    public ClienteService(JsonRepository<Cliente> repo) {
        this.repo = repo;
        this.clientes = repo.listAll();
    }

    public Cliente cadastrar(Cliente c) {
        clientes.add(c);
        repo.saveAll(clientes);
        return c;
    }

    public Cliente cadastrarVIP(String id, String nome, String email) {
        ClienteVIP vip = new ClienteVIP(id, nome, email);
        clientes.add(vip);
        repo.saveAll(clientes);
        return vip;
    }

    public Optional<Cliente> buscar(String id) {
        return clientes.stream().filter(c -> c.getId().equals(id)).findFirst();
    }

    public void adicionarPontos(String clienteId, int pontos) {
        buscar(clienteId).ifPresent(c -> {
            c.addPontos(pontos);
            repo.saveAll(clientes);
        });
    }

    public List<Cliente> listar() { return clientes; }

    public void salvar() {
    }
}
