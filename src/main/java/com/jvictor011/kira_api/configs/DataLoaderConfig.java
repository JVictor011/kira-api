package com.jvictor011.kira_api.configs;

import com.jvictor011.kira_api.model.enums.Permissao;
import com.jvictor011.kira_api.model.entity.Usuario;
import com.jvictor011.kira_api.repository.UsuarioRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class DataLoaderConfig {

    @Bean
    public CommandLineRunner initData(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder) {
        return args -> {
            if (usuarioRepository.existsByEmail("jv@gmail.com")) {
                Usuario admin1 = new Usuario();
                admin1.setNome("jv-dev");
                admin1.setEmail("jv@gmail.com");
                admin1.setPassword(passwordEncoder.encode("admJv@973!A@"));
                admin1.setPermissao(Permissao.ADM);
                usuarioRepository.save(admin1);
            }

            if (usuarioRepository.existsByEmail("felipe@gmail.com")) {
                Usuario admin2 = new Usuario();
                admin2.setNome("felipe");
                admin2.setEmail("felipe@gmail.com");
                admin2.setPassword(passwordEncoder.encode("felipe@435Af!"));
                admin2.setPermissao(Permissao.ADM);
                usuarioRepository.save(admin2);
            }

            if (usuarioRepository.existsByEmail("vctr@gmail.com")) {
                Usuario admin3 = new Usuario();
                admin3.setNome("vctr");
                admin3.setEmail("vctr@gmail.com");
                admin3.setPassword(passwordEncoder.encode("@Vw12345678."));
                admin3.setPermissao(Permissao.ADM);
                usuarioRepository.save(admin3);
            }
        };
    }
}
