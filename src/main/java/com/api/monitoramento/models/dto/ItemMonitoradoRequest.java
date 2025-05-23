package com.api.monitoramento.models.dto;

import com.api.monitoramento.models.Cliente;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ItemMonitoradoRequest {
    private String cliente;
    private Instant datahora;
    private String hostname;
    private String cpu;
    private String memoria;
    private String disco;
    private String downloadKbps;
    private String uploadKbps;
    private String cpuModelo;
    private Integer ramPentes;
    private String ramDetalhes;
    private String armazenamento;
    private String localidade;
}
