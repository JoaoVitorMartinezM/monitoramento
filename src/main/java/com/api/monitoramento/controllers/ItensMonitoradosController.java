package com.api.monitoramento.controllers;

import com.api.monitoramento.models.dto.ItensMonitoradosResponse;
import com.api.monitoramento.models.dto.LocalidadeResponse;
import com.api.monitoramento.services.ItensMonitoradosService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("monitorados")
@RequiredArgsConstructor
public class ItensMonitoradosController {

    private final ItensMonitoradosService service;

    @GetMapping
    public ResponseEntity<List<ItensMonitoradosResponse>> listarItensMonitorados(){

        return ResponseEntity.ok(service.listarItensMonitorados());
    }

    @GetMapping("{id}")
    ResponseEntity<ItensMonitoradosResponse> buscarItemMonitoradoPorId(@PathVariable String id){
        Optional<ItensMonitoradosResponse> ItemMonitoradoResponseOpt = service.buscarItemMonitoradoPorId(id);
        return ItemMonitoradoResponseOpt.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.noContent().build());
    }



}
