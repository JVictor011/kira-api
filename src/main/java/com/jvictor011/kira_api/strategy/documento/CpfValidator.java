package com.jvictor011.kira_api.strategy.documento;

import org.springframework.stereotype.Component;

@Component("cpf")
public class CpfValidator implements DocumentoValidatorStrategy {
    @Override
    public boolean isValid(String cpf) {
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
}
