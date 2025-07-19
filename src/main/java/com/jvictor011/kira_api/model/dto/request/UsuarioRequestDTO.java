package com.jvictor011.kira_api.model.dto.request;

import com.jvictor011.kira_api.model.enums.Permissao;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioRequestDTO {
    private String nome;
    private LocalDate dataNascimento;
    private String cpf;
    private String email;
    private String password;
    private String confirmPassword;
    private Permissao permissao;
}
