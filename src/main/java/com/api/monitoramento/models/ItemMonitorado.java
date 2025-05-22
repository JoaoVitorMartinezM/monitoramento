package com.api.monitoramento.models;

import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;
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

    @Temporal(TemporalType.TIMESTAMP)
    private Instant datahora;

    //@Temporal(TemporalType.TIME)
    //private Instant hora;

    private String cpu;
    private String memoria;
    private String disco;
    private String downloadKbps;
    private String uploadKbps;
    private String cpuModelo;
    private Integer ramPentes;
    private String ramDetalhes;
    private String armazenamento;

}
