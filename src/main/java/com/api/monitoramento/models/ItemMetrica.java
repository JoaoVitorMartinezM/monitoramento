package com.api.monitoramento.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "tb_itens_metricas")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ItemMetrica {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID uuid;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "item_monitorado_id")
    private ItemMonitorado itemMonitorado;

    private String cpu;
    private String memoria;
    private String disco;
    private String downloadKbps;
    private String uploadKbps;
}
