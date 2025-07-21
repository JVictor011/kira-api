package com.jvictor011.kira_api.mapper;

import com.jvictor011.kira_api.model.dto.request.ImovelRequestDTO;
import com.jvictor011.kira_api.model.dto.response.ArquivoResponseDTO;
import com.jvictor011.kira_api.model.dto.response.ImovelResponseDTO;
import com.jvictor011.kira_api.model.entity.Arquivo;
import com.jvictor011.kira_api.model.entity.Imovel;
import com.jvictor011.kira_api.model.entity.Usuario;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ImovelMapper {

    private final EnderecoMapper enderecoMapper;

    public ImovelMapper(EnderecoMapper enderecoMapper) {
        this.enderecoMapper = enderecoMapper;
    }

    public Imovel toEntity(ImovelRequestDTO dto, Usuario locador) {
        return Imovel.builder()
                .titulo(dto.getTitulo())
                .descricao(dto.getDescricao())
                .endereco(enderecoMapper.toEntity(dto.getEndereco()))
                .preco(dto.getPreco())
                .numQuartos(dto.getNumQuartos())
                .tipo(dto.getTipo())
                .mobiliado(dto.isMobiliado())
                .area(dto.getArea())
                .locador(locador)
                .fotos(new ArrayList<>())
                .build();
    }

    public ImovelResponseDTO toDTO(Imovel imovel) {
        return ImovelResponseDTO.builder()
                .id(imovel.getId())
                .titulo(imovel.getTitulo())
                .descricao(imovel.getDescricao())
                .endereco(enderecoMapper.toDTO(imovel.getEndereco()))
                .preco(imovel.getPreco())
                .numQuartos(imovel.getNumQuartos())
                .tipo(imovel.getTipo())
                .mobiliado(imovel.isMobiliado())
                .area(imovel.getArea())
                .fotos(imovel.getFotos().stream()
                        .map(arquivo -> new ArquivoResponseDTO(
                                arquivo.getId(),
                                arquivo.getNome(),
                                arquivo.getUrl(),
                                arquivo.getKey()))
                        .collect(Collectors.toList()))
                .locadorId(imovel.getLocador().getId())
                .build();
    }

    public void update(Imovel imovel, ImovelRequestDTO dto, Usuario locador) {
        imovel.setTitulo(dto.getTitulo());
        imovel.setDescricao(dto.getDescricao());
        imovel.setEndereco(enderecoMapper.toEntity(dto.getEndereco()));
        imovel.setPreco(dto.getPreco());
        imovel.setNumQuartos(dto.getNumQuartos());
        imovel.setTipo(dto.getTipo());
        imovel.setMobiliado(dto.isMobiliado());
        imovel.setArea(dto.getArea());
        imovel.setLocador(locador);
    }
}
