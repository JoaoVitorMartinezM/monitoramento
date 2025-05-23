package com.api.monitoramento.services;

import com.api.monitoramento.models.ItemMonitorado;
import com.api.monitoramento.models.Localidade;
import com.api.monitoramento.models.dto.ItensMonitoradosResponse;
import com.api.monitoramento.models.dto.LocalidadeResponse;
import com.api.monitoramento.repositories.ItensMonitoradosRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ItensMonitoradosService {

    private final ItensMonitoradosRepository itensMonitoradosRepository;

    public List<ItensMonitoradosResponse> listarItensMonitorados() {
        List<ItemMonitorado> itensMonitorados = itensMonitoradosRepository.findAll();
        List<ItensMonitoradosResponse> itensMonitoradosResponseList = itensMonitorados.stream().map(item -> {
            ItensMonitoradosResponse itensMonitoradosResponse = new ItensMonitoradosResponse();
            BeanUtils.copyProperties(item, itensMonitoradosResponse);
            return itensMonitoradosResponse;
        }).toList();

        return itensMonitoradosResponseList;
    }

    public Optional<ItensMonitoradosResponse> buscarItemMonitoradoPorId(String id) {
        Optional<ItemMonitorado> itemMonitoradoOpt = itensMonitoradosRepository.findById(UUID.fromString(id));

        if (itemMonitoradoOpt.isPresent()){
            ItemMonitorado itemMonitorado = itemMonitoradoOpt.get();
            ItensMonitoradosResponse itemMonitoradoResponse = new ItensMonitoradosResponse(itemMonitorado.getUuid(), itemMonitorado.getCliente().getNome(), itemMonitorado.getHostname());
            return Optional.of(itemMonitoradoResponse);
        }
        return Optional.empty();
    }
}
