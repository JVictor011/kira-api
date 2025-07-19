package com.jvictor011.kira_api.strategy.documento;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class DocumentoValidatorFactory {

    private final Map<String, DocumentoValidatorStrategy> estrategias;

    public DocumentoValidatorFactory(List<DocumentoValidatorStrategy> list) {
        this.estrategias = list.stream()
                .collect(Collectors.toMap(e -> e.getClass().getAnnotation(Component.class).value(), e -> e));
    }

    public DocumentoValidatorStrategy getValidator(String documento) {
        String tipo = documento.length() == 11 ? "cpf" : "cnpj";
        return estrategias.get(tipo);
    }
}
