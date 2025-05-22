package com.api.monitoramento.controllers;


import com.api.monitoramento.models.dto.ClienteResponse;
import com.api.monitoramento.services.ClienteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("clientes")
@RequiredArgsConstructor
public class ClienteController {

    private final ClienteService service;

    @GetMapping
    public ResponseEntity<List<ClienteResponse>> listarClientes(){
        List<ClienteResponse> clientes = service.listarClientes();
        return ResponseEntity.ok(clientes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClienteResponse> buscarClientePorId(@PathVariable String id){
        Optional<ClienteResponse> clienteResponseOpt= service.buscarClientePorId(id);
        return clienteResponseOpt.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.noContent().build());
    }


    @PostMapping
    public ResponseEntity<ClienteResponse> criarCliente(@RequestParam(name = "nome") String nomeCliente){
        ClienteResponse clienteResponse = service.criarCliente(nomeCliente);
        URI location = URI.create("/api/v1/clientes/" + clienteResponse.uuid().toString());
        return ResponseEntity.created(location).body(clienteResponse);
    }


}
