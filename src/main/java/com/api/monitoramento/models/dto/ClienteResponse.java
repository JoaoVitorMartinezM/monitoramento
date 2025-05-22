package com.api.monitoramento.models.dto;

import lombok.NoArgsConstructor;

import java.util.UUID;

public record ClienteResponse(UUID uuid, String nome) {
}
