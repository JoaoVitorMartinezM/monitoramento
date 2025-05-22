package com.api.monitoramento.models.dto;

import lombok.NoArgsConstructor;

import java.util.UUID;

public record LocalidadeResponse(UUID uuid, String localidade, String cliente) {
}
