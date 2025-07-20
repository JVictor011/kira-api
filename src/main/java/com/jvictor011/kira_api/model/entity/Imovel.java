package com.jvictor011.kira_api.model.entity;

import com.jvictor011.kira_api.model.enums.TipoImovel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "imoveis")
public class Imovel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;
    private String descricao;
    private BigDecimal preco;

    @Column(name = "numero_de_quartos")
    private int numQuartos;

    private TipoImovel tipo;
    private boolean mobiliado;
    private Double area;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "imovel_id")
    private List<Arquivo> fotos = new ArrayList<>();

    @OneToOne
    private Usuario locador;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true, optional = false)
    @JoinColumn(name = "endereco_id")
    private Endereco endereco;
}
