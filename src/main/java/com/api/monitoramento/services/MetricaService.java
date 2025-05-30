package com.api.monitoramento.services;

import com.api.monitoramento.models.ItemMetrica;
import com.api.monitoramento.models.ItemMonitorado;
import com.api.monitoramento.models.dto.MetricaRequest;
import com.api.monitoramento.models.dto.MetricaResponse;
import com.api.monitoramento.repositories.ItensMonitoradosRepository;
import com.api.monitoramento.repositories.MetricaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MetricaService {
    private final MetricaRepository repository;
    private final ItensMonitoradosRepository itensMonitoradosRepository;

    public ItemMetrica criarMetrica(MetricaRequest request) {
        ItemMetrica itemMetrica = new ItemMetrica();
        ItemMonitorado itemMonitorado = itensMonitoradosRepository.getReferenceById(request.getItemMonitoradoId());

        BeanUtils.copyProperties(request, itemMetrica);
        itemMetrica.setItemMonitorado(itemMonitorado);
        ItemMetrica save = repository.save(itemMetrica);

        return save;
    }

    public List<MetricaResponse> listarMetricas(){
        List<ItemMetrica> metricasList = repository.findAll();
        List<MetricaResponse> metricaResponseList = metricasList.stream().map(metrica -> new MetricaResponse(metrica.getItemMonitorado().getHostname(), metrica.getCpu(), metrica.getMemoria(), metrica.getDisco(), metrica.getDownloadKbps(), metrica.getUploadKbps())).toList();

        return metricaResponseList;
    }
}
