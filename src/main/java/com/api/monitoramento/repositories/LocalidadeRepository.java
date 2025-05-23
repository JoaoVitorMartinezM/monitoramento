package com.api.monitoramento.repositories;

import com.api.monitoramento.models.Localidade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface LocalidadeRepository extends JpaRepository<Localidade, UUID> {
    boolean existsByNome(String localidade);

    Optional<Localidade> findByNome(String localidade);
}
