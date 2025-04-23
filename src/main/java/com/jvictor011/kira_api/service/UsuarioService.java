package com.jvictor011.kira_api.service;

import com.jvictor011.kira_api.exception.*;
import com.jvictor011.kira_api.mapper.UsuarioMapper;
import com.jvictor011.kira_api.model.dto.UsuarioRequestDTO;
import com.jvictor011.kira_api.model.dto.UsuarioResponseDTO;
import com.jvictor011.kira_api.model.entity.Usuario;
import com.jvictor011.kira_api.repository.UsuarioRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service
public class UsuarioService implements UserDetailsService {

    private final UsuarioRepository usuarioRepository;
    private final UsuarioMapper usuarioMapper;
    private final PasswordEncoder passwordEncoder;

    public UsuarioService(UsuarioRepository usuarioRepository, UsuarioMapper usuarioMapper, PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.usuarioMapper = usuarioMapper;
        this.passwordEncoder = passwordEncoder;
    }

    public static boolean isValidCPF(String cpf) {
        if (cpf == null) return false;

        cpf = cpf.replaceAll("[^\\d]", "");

        if (cpf.length() != 11) return false;

        if (cpf.matches("(\\d)\\1{10}")) return false;

        try {
            int soma = 0;
            for (int i = 0; i < 9; i++) {
                soma += Character.getNumericValue(cpf.charAt(i)) * (10 - i);
            }

            int primeiroDigito = 11 - (soma % 11);
            if (primeiroDigito > 9) primeiroDigito = 0;

            if (primeiroDigito != Character.getNumericValue(cpf.charAt(9))) return false;

            soma = 0;
            for (int i = 0; i < 10; i++) {
                soma += Character.getNumericValue(cpf.charAt(i)) * (11 - i);
            }

            int segundoDigito = 11 - (soma % 11);
            if (segundoDigito > 9) segundoDigito = 0;

            return segundoDigito == Character.getNumericValue(cpf.charAt(10));
        } catch (Exception e) {
            return false;
        }
    }

    public void create(UsuarioRequestDTO dto){
        if (!dto.getPassword().equals(dto.getConfirmPassword())){
            throw new SenhaNaoCoincideException(MensagensErro.SENHA_NAO_COINCIDE);
        }
        if (!isSenhaValida(dto.getPassword())){
            throw new SenhaNaoValidaException(MensagensErro.SENHA_NAO_VALIDA);
        }

        boolean exists = usuarioRepository.existsByEmail(dto.getEmail());
        if (!exists){
            throw new EmailJaCadastradoException(MensagensErro.EMAIL_JA_CADASTRADO);
        }

        if (!isValidCPF(dto.getCpf())){
            throw new CpfInvalidoException(MensagensErro.CPF_INVALIDO);
        }

        Usuario usuario = usuarioMapper.toEntity(dto);
        usuario.setPassword(passwordEncoder.encode(dto.getPassword()));

        usuarioRepository.save(usuario);
    }

    public List<Usuario> getAll(){
        return usuarioRepository.findAll();
    }

    public Usuario getById(Long id){
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new UsuarioNaoEncontradoException(MensagensErro.USUARIO_NAO_ENCONTRADO));
    }

    public List<UsuarioResponseDTO> getAllDTO(){
        return getAll().stream()
                .map(usuarioMapper::toDTO)
                .collect(Collectors.toList());
    }

    public UsuarioResponseDTO getByIdDTO(Long id){
        return usuarioMapper.toDTO(getById(id));
    }

    public UsuarioResponseDTO update(Long id, UsuarioRequestDTO dto){
        Usuario usuario = getById(id);

        usuarioMapper.update(usuario, dto);
        usuarioRepository.save(usuario);

        return usuarioMapper.toDTO(usuario);
    }

    public void delete(Long id){
        Usuario usuario = getById(id);
        usuarioRepository.delete(usuario);
    }

    public boolean isSenhaValida(String password) {
        if (password == null || password.length() < 8) {
            return false;
        }

        Pattern upperCasePattern = Pattern.compile("[A-Z]");
        Pattern lowerCasePattern = Pattern.compile("[a-z]");
        Pattern specialCharPattern = Pattern.compile("[^a-zA-Z0-9]");

        return upperCasePattern.matcher(password).find() &&
                lowerCasePattern.matcher(password).find() &&
                specialCharPattern.matcher(password).find();
    }


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepository.findByEmail(email);
        if (usuario == null) {
            throw new UsernameNotFoundException(MensagensErro.USUARIO_NAO_ENCONTRADO);
        }

        return User
                .builder()
                .username(usuario.getEmail())
                .password(usuario.getPassword())
                .roles(usuario.getPermissao().name())
                .build();
    }
}
