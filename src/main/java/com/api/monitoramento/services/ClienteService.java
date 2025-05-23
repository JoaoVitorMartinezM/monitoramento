package com.api.monitoramento.services;

import com.api.monitoramento.models.Cliente;
import com.api.monitoramento.models.dto.ClienteResponse;
import com.api.monitoramento.repositories.ClienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ClienteService {

    private final ClienteRepository clienteRepository;

    public List<ClienteResponse> listarClientes() {
        List<Cliente> clientes = clienteRepository.findAll();
        List<ClienteResponse> clienteResponses = clientes.stream().map((cliente) -> new ClienteResponse(cliente.getUuid(), cliente.getNome())).toList();
        return clienteResponses;
    }

    public ClienteResponse criarCliente(String nomeCliente) {
        Cliente cliente = new Cliente();
        cliente.setNome(nomeCliente.toUpperCase());
        cliente = clienteRepository.save(cliente);

        return new ClienteResponse(cliente.getUuid(), cliente.getNome());
    }

    public Optional<ClienteResponse> buscarClientePorId(String id) {
        Optional<Cliente> clienteOpt = clienteRepository.findById(UUID.fromString(id));

        if (clienteOpt.isEmpty()){
            return Optional.empty();
        }

        Cliente cliente = clienteOpt.get();

        return Optional.of(new ClienteResponse(cliente.getUuid(), cliente.getNome()));
    }
}
