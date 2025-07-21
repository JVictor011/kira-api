package com.jvictor011.kira_api.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "favoritos")
public class Favorito {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    @ManyToOne(optional = false)
    @JoinColumn(name = "imovel_id", nullable = false)
    private Imovel imovel;

    @Column(name = "data_favoritado", nullable = false, updatable = false)
    private LocalDateTime dataFavoritado;

    @PrePersist
    public void prePersist() {
        this.dataFavoritado = LocalDateTime.now();
    }
}
