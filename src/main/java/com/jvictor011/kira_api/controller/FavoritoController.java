package com.jvictor011.kira_api.controller;

import com.jvictor011.kira_api.model.dto.request.FavoritoRequesDTO;
import com.jvictor011.kira_api.model.dto.response.FavoritoResponseDTO;
import com.jvictor011.kira_api.service.FavoritoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/favoritos")
@RequiredArgsConstructor
@Api(tags = "Favoritos")
public class FavoritoController {

    private final FavoritoService favoritoService;

    @PostMapping("/imovel/{id}")
    @ApiOperation("Adiciona um imóvel à lista de favoritos do usuário logado")
    public ResponseEntity<Void> salvar(@PathVariable("id") Long imovelId) {
        favoritoService.salvar(imovelId);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    @ApiOperation("Lista todos os imóveis favoritados pelo usuário logado")
    public ResponseEntity<List<FavoritoResponseDTO>> listarFavoritos() {
        List<FavoritoResponseDTO> favoritos = favoritoService.getByUsuarioDTO();
        return ResponseEntity.ok(favoritos);
    }

    @DeleteMapping("/{id}")
    @ApiOperation("Remove um imóvel da lista de favoritos do usuário logado")
    public ResponseEntity<Void> remover(@PathVariable Long id) {
        favoritoService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
