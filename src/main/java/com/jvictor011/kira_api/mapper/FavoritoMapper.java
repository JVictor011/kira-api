package com.jvictor011.kira_api.mapper;

import com.jvictor011.kira_api.model.dto.request.FavoritoRequesDTO;
import com.jvictor011.kira_api.model.dto.response.FavoritoResponseDTO;
import com.jvictor011.kira_api.model.entity.Favorito;
import com.jvictor011.kira_api.model.entity.Imovel;
import com.jvictor011.kira_api.model.entity.Usuario;
import org.springframework.stereotype.Component;

@Component
public class FavoritoMapper {

    private final ImovelMapper imovelMapper;

    public FavoritoMapper(ImovelMapper imovelMapper) {
        this.imovelMapper = imovelMapper;
    }

    public Favorito toEntity(Imovel imovel, Usuario usuario){
        return Favorito.builder()
                .imovel(imovel)
                .usuario(usuario)
                .build();
    }

    public FavoritoResponseDTO toDTO(Favorito favorito){
        return FavoritoResponseDTO.builder()
                .id(favorito.getId())
                .imovel(imovelMapper.toDTO(favorito.getImovel()))
                .build();
    }
}
