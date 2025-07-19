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
                .orElseThrow(() -> new NotFoundException("Locador não encontrado"));

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

    @Transactional(readOnly = true)
    public List<ImovelResponseDTO> getAllDTOs() {
        return imovelRepository.findAll().stream()
                .map(imovelMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public ImovelResponseDTO getDTOById(Long id) {
        Imovel imovel = imovelRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(MensagensErro.IMOVEL_NAO_ENCONTRADO));
        return imovelMapper.toDTO(imovel);
    }

    @Transactional(readOnly = true)
    public List<ImovelResponseDTO> filtrarPorQuartos(int numQuartos) {
        return imovelRepository.findByNumQuartos(numQuartos).stream()
                .map(imovelMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<ImovelResponseDTO> filtrarPorFaixaDePreco(BigDecimal precoMin, BigDecimal precoMax) {
        return imovelRepository.findByPrecoBetween(precoMin, precoMax).stream()
                .map(imovelMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<ImovelResponseDTO> filtrarPorQuartosEPreco(int numQuartos, BigDecimal precoMin, BigDecimal precoMax) {
        return imovelRepository.findByNumQuartosAndPrecoBetween(numQuartos, precoMin, precoMax).stream()
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