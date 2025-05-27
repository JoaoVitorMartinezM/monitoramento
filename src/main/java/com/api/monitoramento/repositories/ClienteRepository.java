package com.api.monitoramento.repositories;

import com.api.monitoramento.models.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, UUID> {


    boolean existsByNome(String nome);


    Optional<Cliente> findByNome(String nome);
}
