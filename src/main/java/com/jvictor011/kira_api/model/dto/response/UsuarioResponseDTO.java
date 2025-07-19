package com.jvictor011.kira_api.model.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
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
public class UsuarioResponseDTO {
    private Long id;
    private String nome;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dataNascimento;
    private String documento;
    private String email;
    private String password;
    private String confirmPassword;
    private Permissao permissao;
}
