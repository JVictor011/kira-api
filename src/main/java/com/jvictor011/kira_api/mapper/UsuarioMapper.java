package com.jvictor011.kira_api.mapper;

import com.jvictor011.kira_api.model.dto.UsuarioRequestDTO;
import com.jvictor011.kira_api.model.dto.UsuarioResponseDTO;
import com.jvictor011.kira_api.model.entity.Usuario;
import com.jvictor011.kira_api.model.enums.Permissao;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class UsuarioMapper {

    public Usuario toEntity(UsuarioRequestDTO dto){
        Usuario usuario = new Usuario();

        usuario.setNome(dto.getNome());
        usuario.setEmail(dto.getEmail());
        usuario.setCpf(dto.getCpf());
        usuario.setDataNascimento(dto.getDataNascimento());

        if (dto.getPermissao() != null){
            usuario.setPermissao(dto.getPermissao());
        }else{
            usuario.setPermissao(Permissao.LOCATARIO);
        }

        return usuario;
    }

    public UsuarioResponseDTO toDTO(Usuario usuario){
        UsuarioResponseDTO dto = new UsuarioResponseDTO();

        dto.setId(usuario.getId());
        dto.setNome(usuario.getNome());
        dto.setEmail(usuario.getEmail());
        dto.setCpf(usuario.getCpf());
        dto.setDataNascimento(usuario.getDataNascimento());
        dto.setPermissao(usuario.getPermissao());

        return dto;
    }

    public void update(Usuario usuario, UsuarioRequestDTO dto){
        usuario.setNome(dto.getNome());
        usuario.setEmail(dto.getEmail());
        usuario.setCpf(dto.getCpf());
        usuario.setDataNascimento(dto.getDataNascimento());

        if (dto.getPermissao() != null){
            usuario.setPermissao(dto.getPermissao());
        }else{
            usuario.setPermissao(Permissao.LOCATARIO);
        }
    }
}
