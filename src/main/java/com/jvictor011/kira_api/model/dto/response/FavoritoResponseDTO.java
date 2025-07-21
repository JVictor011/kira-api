package com.jvictor011.kira_api.model.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FavoritoResponseDTO {
    private Long id;
    private ImovelResponseDTO imovel;
}
