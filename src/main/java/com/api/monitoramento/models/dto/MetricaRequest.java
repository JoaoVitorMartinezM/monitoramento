package com.api.monitoramento.models.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MetricaRequest {
    private UUID itemMonitoradoId;
    private String cpu;
    private String memoria;
    private String disco;
    private String downloadKbps;
    private String uploadKbps;


}
