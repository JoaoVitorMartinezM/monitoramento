package com.api.monitoramento.services;

import com.api.monitoramento.models.Cliente;
import com.api.monitoramento.models.Localidade;
import com.api.monitoramento.models.dto.LocalidadeResponse;
import com.api.monitoramento.repositories.ClienteRepository;
import com.api.monitoramento.repositories.LocalidadeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class LocalidadeService {

    private final LocalidadeRepository localidadeRepository;
    private final ClienteRepository clienteRepository;

    public Optional<LocalidadeResponse> criarLocalidade(String nomeCliente, String nomeLocalidade) {
        boolean existsByNome = clienteRepository.existsByNome(nomeCliente);
        if (existsByNome){
            Cliente cliente = clienteRepository.findByNome(nomeCliente.toUpperCase()).get();
            Localidade localidade = new Localidade();
            localidade.setNome(nomeLocalidade);
            localidade.setCliente(cliente);
            localidade = localidadeRepository.save(localidade);

            return Optional.of(new LocalidadeResponse(localidade.getUuid(), localidade.getNome(), cliente.getNome()));
        }

        return Optional.empty();
    }

    public List<LocalidadeResponse> listarLocalidades() {
        List<Localidade> localidades = localidadeRepository.findAll();

        return localidades.stream().map(localidade -> new LocalidadeResponse(localidade.getUuid(), localidade.getNome(), localidade.getCliente().getNome())).toList();
    }

    public Optional<LocalidadeResponse> buscarLocalidadePorId(String id) {
        Optional<Localidade> localidadeOpt = localidadeRepository.findById(UUID.fromString(id));

        if (localidadeOpt.isPresent()){
            Localidade localidade = localidadeOpt.get();
            LocalidadeResponse localidadeResponse = new LocalidadeResponse(localidade.getUuid(), localidade.getNome(), localidade.getCliente().getNome());
            return Optional.of(localidadeResponse);
        }
        return Optional.empty();
    }
}
