package com.api.monitoramento.models.dto;

public record MetricaResponse (String hostname, String cpu, String Memoria, String disco, String downloadKbps, String uploadKbps){
}
