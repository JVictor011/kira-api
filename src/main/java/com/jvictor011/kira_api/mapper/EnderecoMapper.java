package com.jvictor011.kira_api.mapper;

import com.jvictor011.kira_api.model.dto.request.EnderecoRequestDTO;
import com.jvictor011.kira_api.model.dto.response.EnderecoResponseDTO;
import com.jvictor011.kira_api.model.entity.Endereco;
import org.springframework.stereotype.Component;

@Component
public class EnderecoMapper {

    public Endereco toEntity(EnderecoRequestDTO dto) {
        return Endereco.builder()
                .bairro(dto.getBairro())
                .cep(dto.getCep())
                .cidade(dto.getCidade())
                .complemento(dto.getComplemento())
                .estado(dto.getEstado())
                .logradouro(dto.getLogradouro())
                .numero(dto.getNumero())
                .pais(dto.getPais())
                .rua(dto.getRua())
                .build();
    }

    public EnderecoResponseDTO toDTO(Endereco endereco) {
        return EnderecoResponseDTO.builder()
                .bairro(endereco.getBairro())
                .cep(endereco.getCep())
                .cidade(endereco.getCidade())
                .complemento(endereco.getComplemento())
                .estado(endereco.getEstado())
                .logradouro(endereco.getLogradouro())
                .numero(endereco.getNumero())
                .pais(endereco.getPais())
                .rua(endereco.getRua())
                .build();
    }
}
