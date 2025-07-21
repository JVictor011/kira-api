package com.jvictor011.kira_api.service;

import com.jvictor011.kira_api.exception.MensagensErro;
import com.jvictor011.kira_api.exception.NotFoundException;
import com.jvictor011.kira_api.mapper.ImovelMapper;
import com.jvictor011.kira_api.model.dto.request.ImovelRequestDTO;
import com.jvictor011.kira_api.model.dto.response.ImovelResponseDTO;
import com.jvictor011.kira_api.model.entity.Arquivo;
import com.jvictor011.kira_api.model.entity.Imovel;
import com.jvictor011.kira_api.model.entity.Usuario;
import com.jvictor011.kira_api.repository.ArquivoRepository;
import com.jvictor011.kira_api.repository.ImovelRepository;
import com.jvictor011.kira_api.repository.UsuarioRepository;
import com.jvictor011.kira_api.specifications.ImovelSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ImovelService {

    private final ImovelRepository imovelRepository;
    private final UsuarioRepository usuarioRepository;
    private final ArquivoRepository arquivoRepository;
    private final ImovelMapper imovelMapper;

    @Transactional
    public ImovelResponseDTO create(ImovelRequestDTO dto) {
        Usuario locador = usuarioRepository.findById(dto.getLocadorId())
                .orElseThrow(() -> new NotFoundException(MensagensErro.LOCADOR_NAO_ENCONTRADO));

        Imovel imovel = imovelMapper.toEntity(dto, locador);
        imovel = imovelRepository.save(imovel);
        return imovelMapper.toDTO(imovel);
    }

    public List<Imovel> getAllEntities() {
        return imovelRepository.findAll();
    }

    public Imovel getEntityById(Long id) {
        return imovelRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(MensagensErro.IMOVEL_NAO_ENCONTRADO));
    }

    public List<Imovel> getByUsuario(Long id){
        return imovelRepository.findByLocador_Id(id);
    }

    public List<Imovel> filtrarPorQuartos(int numQuartos) {
        return imovelRepository.findByNumQuartos(numQuartos);
    }

    public List<Imovel> filtrarPorFaixaDePreco(BigDecimal precoMin, BigDecimal precoMax) {
        return imovelRepository.findByPrecoBetween(precoMin, precoMax);
    }

    public List<Imovel> filtrarPorQuartosEPreco(int numQuartos, BigDecimal precoMin, BigDecimal precoMax) {
        return imovelRepository.findByNumQuartosAndPrecoBetween(numQuartos, precoMin, precoMax);
    }

    @Transactional(readOnly = true)
    public List<ImovelResponseDTO> getByUsuarioDTO(Long id){
        return getByUsuario(id).stream()
                .map(imovelMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<ImovelResponseDTO> getAllDTOs() {
        return getAllEntities().stream()
                .map(imovelMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public ImovelResponseDTO getDTOById(Long id) {
        Imovel imovel = getEntityById(id);
        return imovelMapper.toDTO(imovel);
    }

    @Transactional(readOnly = true)
    public List<ImovelResponseDTO> filtrarPorQuartosDTO(int numQuartos) {
        return filtrarPorQuartos(numQuartos).stream()
                .map(imovelMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<ImovelResponseDTO> filtrarPorFaixaDePrecoDTO(BigDecimal precoMin, BigDecimal precoMax) {
        return filtrarPorFaixaDePreco(precoMin, precoMax).stream()
                .map(imovelMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<ImovelResponseDTO> filtrarPorQuartosEPrecoDTO(int numQuartos, BigDecimal precoMin, BigDecimal precoMax) {
        return filtrarPorQuartosEPreco(numQuartos, precoMin, precoMax).stream()
                .map(imovelMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<ImovelResponseDTO> filtrarComSpecification(Integer numQuartos, BigDecimal precoMin, BigDecimal precoMax) {
        Specification<Imovel> spec = Specification
                .where(ImovelSpecification.comNumQuartos(numQuartos))
                .and(ImovelSpecification.comPrecoEntre(precoMin, precoMax));

        return imovelRepository.findAll(spec).stream()
                .map(imovelMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Transactional
    public ImovelResponseDTO update(Long id, ImovelRequestDTO dto) {
        Imovel imovel = imovelRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(MensagensErro.IMOVEL_NAO_ENCONTRADO));

        Usuario locador = usuarioRepository.findById(dto.getLocadorId())
                .orElseThrow(() -> new NotFoundException(MensagensErro.USUARIO_NAO_ENCONTRADO));

        imovelMapper.update(imovel, dto, locador);

        imovel = imovelRepository.save(imovel);
        return imovelMapper.toDTO(imovel);
    }


    @Transactional
    public void delete(Long id) {
        Imovel imovel = imovelRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(MensagensErro.IMOVEL_NAO_ENCONTRADO));
        imovelRepository.delete(imovel);
    }
}