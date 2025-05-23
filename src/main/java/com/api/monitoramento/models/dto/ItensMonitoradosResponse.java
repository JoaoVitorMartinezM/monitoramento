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
public class ItensMonitoradosResponse {
    private UUID uuid;
    private String nomeCliente;
    private String hostname;

}
