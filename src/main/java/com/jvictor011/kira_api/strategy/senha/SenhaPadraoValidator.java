package com.jvictor011.kira_api.strategy.senha;

import org.springframework.stereotype.Component;
import java.util.regex.Pattern;

@Component("padrao")
public class SenhaPadraoValidator implements SenhaValidatorStrategy {

    @Override
    public boolean isValid(String senha) {
        if (senha == null || senha.length() < 8) return false;

        Pattern upperCasePattern = Pattern.compile("[A-Z]");
        Pattern lowerCasePattern = Pattern.compile("[a-z]");
        Pattern specialCharPattern = Pattern.compile("[^a-zA-Z0-9]");

        return upperCasePattern.matcher(senha).find() &&
                lowerCasePattern.matcher(senha).find() &&
                specialCharPattern.matcher(senha).find();
    }
}
