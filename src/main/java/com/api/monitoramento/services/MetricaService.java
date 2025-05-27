package com.api.monitoramento.services;

import com.api.monitoramento.models.ItemMetrica;
import com.api.monitoramento.models.dto.MetricaRequest;
import com.api.monitoramento.models.dto.MetricaResponse;
import com.api.monitoramento.repositories.MetricaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MetricaService {
    private final MetricaRepository repository;

    public Boolean criarMetrica(MetricaRequest request) {
        ItemMetrica itemMetrica = new ItemMetrica();
        BeanUtils.copyProperties(request, itemMetrica);
        ItemMetrica save = repository.save(itemMetrica);

        return Boolean.TRUE;
    }

    public List<MetricaResponse> listarMetricas(){
        List<ItemMetrica> metricasList = repository.findAll();
        List<MetricaResponse> metricaResponseList = metricasList.stream().map(metrica -> new MetricaResponse(metrica.getItemMonitorado().getHostname(), metrica.getCpu(), metrica.getMemoria(), metrica.getDisco(), metrica.getDownloadKbps(), metrica.getUploadKbps())).toList();

        return metricaResponseList;
    }
}
