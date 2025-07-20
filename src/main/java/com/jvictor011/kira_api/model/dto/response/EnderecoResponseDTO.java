package com.jvictor011.kira_api.model.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EnderecoResponseDTO {
    private String bairro;
    private String cep;
    private String cidade;
    private String complemento;
    private String estado;
    private String logradouro;
    private String numero;
    private String pais;
    private String rua;
}