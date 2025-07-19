package com.jvictor011.kira_api.strategy.senha;

import org.springframework.stereotype.Component;

@Component("admin")
public class SenhaAdminValidator implements SenhaValidatorStrategy {

    @Override
    public boolean isValid(String senha) {
        return senha != null && senha.length() >= 12;
    }
}