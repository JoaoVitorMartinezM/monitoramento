package com.api.monitoramento.services;

import com.api.monitoramento.models.Cliente;
import com.api.monitoramento.models.ItemMonitorado;
import com.api.monitoramento.models.Localidade;
import com.api.monitoramento.models.dto.ItemMonitoradoRequest;
import com.api.monitoramento.models.dto.ItensMonitoradosResponse;
import com.api.monitoramento.models.dto.LocalidadeResponse;
import com.api.monitoramento.repositories.ClienteRepository;
import com.api.monitoramento.repositories.ItensMonitoradosRepository;
import com.api.monitoramento.repositories.LocalidadeRepository;
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
    private final ClienteRepository clientesRepository;
    private final LocalidadeRepository localidadeRepository;
    private final LocalidadeService localidadeService;

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

    public Optional<ItensMonitoradosResponse> criarItemMonitorado(ItemMonitoradoRequest itemMonitoradoRequest) {
        ItemMonitorado itemMonitorado = new ItemMonitorado();
        ItensMonitoradosResponse itemMonitoradoResponse = new ItensMonitoradosResponse();
        Optional<Cliente> clienteOpt = clientesRepository.findByNome(itemMonitoradoRequest.getCliente().toUpperCase());
        if (clienteOpt.isEmpty()){
            return Optional.empty();
        }
        Cliente cliente = clienteOpt.get();
        BeanUtils.copyProperties(itemMonitoradoRequest, itemMonitorado);
        itemMonitorado.setCliente(cliente);
        if (!localidadeRepository.existsByNome(itemMonitoradoRequest.getLocalidade())){
            localidadeService.criarLocalidade(cliente.getNome(), itemMonitoradoRequest.getLocalidade());
        }
        itemMonitorado.setLocalidade(itemMonitoradoRequest.getLocalidade());

        ItemMonitorado save = itensMonitoradosRepository.save(itemMonitorado);
        BeanUtils.copyProperties(save, itemMonitoradoResponse);

        return Optional.of(itemMonitoradoResponse);
    }
}
