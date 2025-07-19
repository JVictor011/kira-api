package com.jvictor011.kira_api.configs;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.security.access.hierarchicalroles.RoleHierarchy;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl;

import java.util.Locale;

@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

    @Bean
    public RoleHierarchy roleHierarchy() {
        RoleHierarchyImpl roleHierarchy = new RoleHierarchyImpl();

        roleHierarchy.setHierarchy("ROLE_ADMIN > ROLE_LOCADOR > ROLE_LOCATARIO");

        return roleHierarchy;
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                .antMatchers(
                        "/api/usuarios/recuperar-senha",
                        "/api/usuarios/reset-password"
                ).permitAll()
                .antMatchers(
                        "/swagger-ui/**",
                        "/v2/api-docs/**",
                        "/swagger-resources/**",
                        "/webjars/**",
                        "/swagger-ui.html",
                        "/swagger-ui.html",
                        "/v2/api-docs"
                ).permitAll()
                .antMatchers(
                        "/api/usuarios",
                        "/api/usuarios/**"
                ).hasAnyRole("ADMIN", "LOCADOR", "LOCATARIO")
                .anyRequest().authenticated();
    }
}