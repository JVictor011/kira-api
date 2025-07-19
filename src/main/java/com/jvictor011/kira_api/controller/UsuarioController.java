package com.jvictor011.kira_api.controller;

import com.jvictor011.kira_api.model.dto.request.UsuarioRequestDTO;
import com.jvictor011.kira_api.model.dto.response.UsuarioResponseDTO;
import com.jvictor011.kira_api.service.UsuarioService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
@Api(value = "Gerenciamento de Usuários", tags = "Usuários")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @ApiOperation(value = "Cadastra um novo usuário",
            notes = "Cria um novo registro de usuário com os dados fornecidos.")
    @PostMapping()
    public ResponseEntity<Void> create(@RequestBody @Valid UsuarioRequestDTO dto){
        usuarioService.create(dto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @ApiOperation(value = "Lista todos os usuários", notes = "Retorna uma lista contendo todos os usuários cadastrados no sistema.")
    @GetMapping()
    public ResponseEntity<List<UsuarioResponseDTO>> getAll(){
        List<UsuarioResponseDTO> dtos = usuarioService.getAllDTO();
        return ResponseEntity.ok(dtos);
    }

    @ApiOperation(value = "Busca um usuário por ID", notes = "Retorna os dados do usuário correspondente ao ID fornecido.")
    @GetMapping("/{id}")
    public ResponseEntity<UsuarioResponseDTO> getById(@PathVariable Long id){
        UsuarioResponseDTO dto = usuarioService.getByIdDTO(id);
        return ResponseEntity.ok(dto);
    }

    @ApiOperation(value = "Retorna os dados do usuário autenticado")
    @GetMapping("/me")
    public ResponseEntity<UsuarioResponseDTO> getByToken() {
        Long usuarioId = usuarioService.getIdByToken();
        UsuarioResponseDTO usuarioDTO = usuarioService.getByIdDTO(usuarioId);
        return ResponseEntity.ok(usuarioDTO);
    }

    @ApiOperation(value = "Atualiza os dados de um usuário", notes = "Atualiza os dados de um usuário existente com base no ID informado.")
    @PutMapping("/{id}")
    public ResponseEntity<UsuarioResponseDTO> update(
            @PathVariable Long id,
            @RequestBody @Valid UsuarioRequestDTO dto
    ){
        UsuarioResponseDTO response = usuarioService.update(id, dto);
        return ResponseEntity.ok(response);
    }

    @ApiOperation(value = "Remove um usuário", notes = "Exclui permanentemente o usuário correspondente ao ID fornecido.")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        usuarioService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
