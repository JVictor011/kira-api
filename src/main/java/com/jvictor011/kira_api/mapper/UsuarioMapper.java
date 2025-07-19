package com.jvictor011.kira_api.mapper;

import com.jvictor011.kira_api.model.dto.request.UsuarioRequestDTO;
import com.jvictor011.kira_api.model.dto.response.UsuarioResponseDTO;
import com.jvictor011.kira_api.model.entity.Usuario;
import com.jvictor011.kira_api.model.enums.Permissao;
import org.springframework.stereotype.Component;

@Component
public class UsuarioMapper {

    public Usuario toEntity(UsuarioRequestDTO dto) {
        return Usuario.builder()
                .nome(dto.getNome())
                .email(dto.getEmail())
                .documento(dto.getDocumento())
                .dataNascimento(dto.getDataNascimento())
                .permissao(dto.getPermissao() != null ? dto.getPermissao() : Permissao.LOCATARIO)
                .build();
    }

    public UsuarioResponseDTO toDTO(Usuario usuario) {
        return UsuarioResponseDTO.builder()
                .id(usuario.getId())
                .nome(usuario.getNome())
                .email(usuario.getEmail())
                .documento(usuario.getDocumento())
                .dataNascimento(usuario.getDataNascimento())
                .permissao(usuario.getPermissao())
                .build();
    }

    public void update(Usuario usuario, UsuarioRequestDTO dto) {
        usuario.setNome(dto.getNome());
        usuario.setEmail(dto.getEmail());
        usuario.setDocumento(dto.getDocumento());
        usuario.setDataNascimento(dto.getDataNascimento());
        usuario.setPermissao(dto.getPermissao() != null ? dto.getPermissao() : Permissao.LOCATARIO);
    }
}