package com.api.monitoramento.repositories;

import com.api.monitoramento.models.ItemMonitorado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ItensMonitoradosRepository extends JpaRepository<ItemMonitorado, UUID> {

}
