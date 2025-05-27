package com.api.monitoramento.models.dto;

public record MetricaRequest(String cpu, String memoria, String disco, String downloadKbps, String uploadKbps) {
}
