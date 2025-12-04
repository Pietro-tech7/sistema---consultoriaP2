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
        if (emailExiste(c.getEmail())) {
            throw new IllegalArgumentException("Cliente com e-mail " + c.getEmail() + " já existe.");
        }
        clientes.add(c);
        repo.saveAll(clientes);
        return c;
    }

    public Cliente cadastrarVIP(String id, String nome, String email) {
        if (emailExiste(email)) {
            throw new IllegalArgumentException("Cliente com e-mail " + email + " já existe.");
        }
        ClienteVIP vip = new ClienteVIP(id, nome, email);
        clientes.add(vip);
        repo.saveAll(clientes);
        return vip;
    }

    public Optional<Cliente> buscar(String id) {
        return clientes.stream().filter(c -> c.getId().equals(id)).findFirst();
    }

    public List<Cliente> listar() {
        return new ArrayList<>(clientes);
    }

    public boolean emailExiste(String email) {
        return clientes.stream().anyMatch(c -> c.getEmail().equalsIgnoreCase(email));
    }

    public void adicionarContratoAoHistorico(String clienteId, String contratoId) {
        buscar(clienteId).ifPresent(cliente -> {
            cliente.adicionarContratoAoHistorico(contratoId);
            repo.saveAll(clientes);
        });
    }

    public void adicionarPontos(String clienteId, int pontos) {
        buscar(clienteId).ifPresent(cliente -> {
            cliente.addPontos(pontos);
            repo.saveAll(clientes);
        });
    }
}