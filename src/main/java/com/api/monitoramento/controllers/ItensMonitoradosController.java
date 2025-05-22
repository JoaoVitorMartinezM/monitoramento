package com.api.monitoramento.controllers;

import com.api.monitoramento.services.ItensMonitoradosService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("monitoracao")
@RequiredArgsConstructor
public class ItensMonitoradosController {

    private final ItensMonitoradosService service;
}
