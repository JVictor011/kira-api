package com.jvictor011.kira_api.model.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ArquivoResponseDTO {
    private Long id;
    private String nome;
    private String url;
    private String key;
}
