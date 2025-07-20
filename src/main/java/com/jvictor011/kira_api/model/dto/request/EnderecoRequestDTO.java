package com.jvictor011.kira_api.model.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EnderecoRequestDTO {
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
