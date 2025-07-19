package com.jvictor011.kira_api.model.dto.response;

import com.jvictor011.kira_api.model.enums.TipoImovel;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
@Builder
public class ImovelResponseDTO {
    private Long id;
    private String titulo;
    private String descricao;
    private String endereco;
    private BigDecimal preco;
    private int numQuartos;
    private TipoImovel tipo;
    private boolean mobiliado;
    private Double area;
    private List<ArquivoResponseDTO> fotos;
    private Long locadorId;
}
