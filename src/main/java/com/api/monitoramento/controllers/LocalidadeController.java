package com.api.monitoramento.controllers;

import com.api.monitoramento.models.dto.LocalidadeResponse;
import com.api.monitoramento.services.LocalidadeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("localidades")
@RequiredArgsConstructor
public class LocalidadeController {

    private final LocalidadeService service;

    @GetMapping
    ResponseEntity<List<LocalidadeResponse>> listarLocalidades(){
        return ResponseEntity.ok(service.listarLocalidades());
    }

    @GetMapping("{id}")
    ResponseEntity<LocalidadeResponse> buscarLocalidadePorId(@PathVariable String id){
        Optional<LocalidadeResponse> localidadeResponseOpt = service.buscarLocalidadePorId(id);
        return localidadeResponseOpt.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.noContent().build());
    }

    @PostMapping("/{nomeCliente}")
    public ResponseEntity<LocalidadeResponse> criarLocalidade(@PathVariable String nomeCliente, @RequestParam(name = "localidade") String nomeLocalidade){
        Optional<LocalidadeResponse> localidadeResponseOpt = service.criarLocalidade(nomeCliente, nomeLocalidade);
        if (localidadeResponseOpt.isEmpty()){
            return ResponseEntity.badRequest().build();
        }
        LocalidadeResponse localidadeResponse = localidadeResponseOpt.get();
        URI location = URI.create("/api/v1/localidades/" + localidadeResponse.uuid().toString());
        return ResponseEntity.created(location).body(localidadeResponse);
    }

}
