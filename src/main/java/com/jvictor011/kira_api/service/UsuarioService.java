package com.jvictor011.kira_api.service;

import com.jvictor011.kira_api.exception.*;
import com.jvictor011.kira_api.mapper.UsuarioMapper;
import com.jvictor011.kira_api.model.dto.request.UsuarioRequestDTO;
import com.jvictor011.kira_api.model.dto.response.UsuarioResponseDTO;
import com.jvictor011.kira_api.model.entity.Usuario;
import com.jvictor011.kira_api.repository.UsuarioRepository;
import com.jvictor011.kira_api.strategy.documento.DocumentoValidatorFactory;
import com.jvictor011.kira_api.strategy.documento.DocumentoValidatorStrategy;
import com.jvictor011.kira_api.strategy.senha.SenhaValidatorStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service
public class UsuarioService implements UserDetailsService {

    private final UsuarioRepository usuarioRepository;
    private final UsuarioMapper usuarioMapper;
    private final PasswordEncoder passwordEncoder;
    private final DocumentoValidatorFactory documentoValidatorFactory;

    @Qualifier("padrao")
    private final SenhaValidatorStrategy senhaValidator;

    public UsuarioService(
            UsuarioRepository usuarioRepository,
            UsuarioMapper usuarioMapper,
            PasswordEncoder passwordEncoder,
            DocumentoValidatorFactory documentoValidatorFactory,
            @Qualifier("padrao") SenhaValidatorStrategy senhaValidator
    ) {
        this.usuarioRepository = usuarioRepository;
        this.usuarioMapper = usuarioMapper;
        this.passwordEncoder = passwordEncoder;
        this.documentoValidatorFactory = documentoValidatorFactory;
        this.senhaValidator = senhaValidator;
    }

    public void create(UsuarioRequestDTO dto){
        if (!dto.getPassword().equals(dto.getConfirmPassword())){
            throw new BusinessException(MensagensErro.SENHA_NAO_COINCIDE);
        }
        if (!senhaValidator.isValid(dto.getPassword())) {
            throw new BusinessException(MensagensErro.SENHA_NAO_VALIDA);
        }

        boolean exists = usuarioRepository.existsByEmail(dto.getEmail());
        if (!exists){
            throw new ConflictException(MensagensErro.EMAIL_JA_CADASTRADO);
        }

        String documento = dto.getDocumento();
        DocumentoValidatorStrategy validator = documentoValidatorFactory.getValidator(documento);

        if (!validator.isValid(documento)) {
            throw new ValidationException(MensagensErro.DOCUMENTO_INVALIDO);
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
                .orElseThrow(() -> new NotFoundException(MensagensErro.USUARIO_NAO_ENCONTRADO));
    }

    public Long getIdByToken() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !authentication.isAuthenticated()) {
            throw new NotFoundException(MensagensErro.USUARIO_NAO_ENCONTRADO);
        }

        String email = authentication.getName();
        Usuario usuario = usuarioRepository.findByEmail(email);

        if (usuario == null) {
            throw new NotFoundException(MensagensErro.USUARIO_NAO_ENCONTRADO);
        }

        return usuario.getId();
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

    @Override
    public UserDetails loadUserByUsername(String email) throws NotFoundException {
        Usuario usuario = usuarioRepository.findByEmail(email);
        if (usuario == null) {
            throw new NotFoundException(MensagensErro.USUARIO_NAO_ENCONTRADO);
        }

        return User
                .builder()
                .username(usuario.getEmail())
                .password(usuario.getPassword())
                .roles(usuario.getPermissao().name())
                .build();
    }
}
