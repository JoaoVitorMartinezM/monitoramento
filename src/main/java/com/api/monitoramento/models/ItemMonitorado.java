package com.api.monitoramento.models;

import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "tb_itens_monitorados")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ItemMonitorado {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID uuid;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "itemMonitorado", cascade = CascadeType.ALL)
    private List<ItemMetrica> metricas;

    @Temporal(TemporalType.TIMESTAMP)
    private Instant datahora;
    private String hostname;
    private String cpuModelo;
    private Integer ramPentes;
    private String ramDetalhes;
    private String armazenamento;
    private String localidade;

}
