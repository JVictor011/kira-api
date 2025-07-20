package com.jvictor011.kira_api.model.dto.request;

import com.jvictor011.kira_api.model.enums.TipoImovel;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Builder
@Data
public class ImovelRequestDTO {
    private String titulo;
    private String descricao;
    private EnderecoRequestDTO endereco;
    private BigDecimal preco;
    private int numQuartos;
    private TipoImovel tipo;
    private boolean mobiliado;
    private Double area;
    private Long locadorId;
}
