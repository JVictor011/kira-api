package com.jvictor011.kira_api.specifications;

import com.jvictor011.kira_api.model.entity.Imovel;
import org.springframework.data.jpa.domain.Specification;

import java.math.BigDecimal;

public class ImovelSpecification {

    public static Specification<Imovel> comNumQuartos(Integer numQuartos) {
        return (root, query, builder) ->
                numQuartos == null ? null : builder.equal(root.get("numQuartos"), numQuartos);
    }

    public static Specification<Imovel> comPrecoEntre(BigDecimal precoMin, BigDecimal precoMax) {
        return (root, query, builder) -> {
            if (precoMin != null && precoMax != null) {
                return builder.between(root.get("preco"), precoMin, precoMax);
            } else if (precoMin != null) {
                return builder.greaterThanOrEqualTo(root.get("preco"), precoMin);
            } else if (precoMax != null) {
                return builder.lessThanOrEqualTo(root.get("preco"), precoMax);
            } else {
                return null;
            }
        };
    }
}