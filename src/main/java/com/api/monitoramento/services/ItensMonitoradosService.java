package com.api.monitoramento.services;

import com.api.monitoramento.repositories.ItensMonitoradosRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ItensMonitoradosService {

    private final ItensMonitoradosRepository itensMonitoradosRepository;

}
