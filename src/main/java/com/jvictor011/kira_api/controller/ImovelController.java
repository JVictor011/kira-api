package com.jvictor011.kira_api.controller;

import com.jvictor011.kira_api.model.dto.request.ImovelRequestDTO;
import com.jvictor011.kira_api.model.dto.response.ImovelResponseDTO;
import com.jvictor011.kira_api.service.ImovelService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/imoveis")
@RequiredArgsConstructor
@Api(tags = "Imóveis")
public class ImovelController {

    private final ImovelService imovelService;

    @PostMapping
    @ApiOperation("Cadastrar um novo imóvel")
    public ResponseEntity<ImovelResponseDTO> create(@RequestBody ImovelRequestDTO dto) {
        return ResponseEntity.ok(imovelService.create(dto));
    }

    @GetMapping
    @ApiOperation("Listar todos os imóveis")
    public ResponseEntity<List<ImovelResponseDTO>> getAll() {
        return ResponseEntity.ok(imovelService.getAllDTOs());
    }

    @GetMapping("/{id}")
    @ApiOperation("Buscar imóvel por ID")
    public ResponseEntity<ImovelResponseDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(imovelService.getDTOById(id));
    }

    @GetMapping("/filtro")
    @ApiOperation("Filtrar imóveis com Specification (nº quartos e faixa de preço)")
    public ResponseEntity<List<ImovelResponseDTO>> filtrarComSpecification(
            @RequestParam(required = false) Integer numQuartos,
            @RequestParam(required = false) BigDecimal precoMin,
            @RequestParam(required = false) BigDecimal precoMax
    ) {
        List<ImovelResponseDTO> resultado = imovelService.filtrarComSpecification(numQuartos, precoMin, precoMax);
        return ResponseEntity.ok(resultado);
    }

    @PutMapping("/{id}")
    @ApiOperation("Atualizar dados de um imóvel")
    public ResponseEntity<ImovelResponseDTO> update(@PathVariable Long id, @RequestBody ImovelRequestDTO dto) {
        return ResponseEntity.ok(imovelService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    @ApiOperation("Excluir um imóvel")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        imovelService.delete(id);
        return ResponseEntity.noContent().build();
    }
}