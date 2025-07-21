package com.jvictor011.kira_api.service;

import com.jvictor011.kira_api.exception.AccessDeniedException;
import com.jvictor011.kira_api.exception.MensagensErro;
import com.jvictor011.kira_api.exception.NotFoundException;
import com.jvictor011.kira_api.mapper.FavoritoMapper;
import com.jvictor011.kira_api.model.dto.request.FavoritoRequesDTO;
import com.jvictor011.kira_api.model.dto.response.FavoritoResponseDTO;
import com.jvictor011.kira_api.model.entity.Favorito;
import com.jvictor011.kira_api.model.entity.Imovel;
import com.jvictor011.kira_api.model.entity.Usuario;
import com.jvictor011.kira_api.repository.FavoritoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FavoritoService {
    private final FavoritoRepository favoritoRepository;
    private final UsuarioService usuarioService;
    private final FavoritoMapper favoritoMapper;
    private final ImovelService imovelService;

    @Transactional
    public void salvar(Long imovelId){
        Usuario usuario = usuarioService.getByToken();
        Imovel imovel = imovelService.getEntityById(imovelId);

        Favorito favorito = favoritoMapper.toEntity(imovel, usuario);
        favoritoRepository.save(favorito);
    }

    private List<Favorito> getByUsuario(Long usuarioId){
        return favoritoRepository.findByUsuario_Id(usuarioId);
    }

    private Favorito getById(Long id){
        return favoritoRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(MensagensErro.FAVORITO_NAO_ENCONTRADO));
    }

    @Transactional(readOnly = true)
    public List<FavoritoResponseDTO> getByUsuarioDTO(){
        Long usuarioId = usuarioService.getIdByToken();
        List<Favorito> favoritos = getByUsuario(usuarioId);

        return favoritos.stream()
                .map(favoritoMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Transactional
    public void delete(Long id){
        Favorito favorito = getById(id);
        Long usuarioLogadoId = usuarioService.getIdByToken();

        if (!favorito.getUsuario().getId().equals(usuarioLogadoId)) {
            throw new AccessDeniedException(MensagensErro.FAVORITO_ACESSO_NEGADO);
        }

        favoritoRepository.delete(favorito);
    }
}
