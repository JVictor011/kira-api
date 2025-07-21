package com.jvictor011.kira_api.repository;

import com.jvictor011.kira_api.model.entity.Imovel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface ImovelRepository extends JpaRepository<Imovel, Long>, JpaSpecificationExecutor<Imovel> {
    List<Imovel> findByNumQuartos(int numQuartos);

    List<Imovel> findByPrecoBetween(BigDecimal precoMin, BigDecimal precoMax);

    List<Imovel> findByNumQuartosAndPrecoBetween(int numQuartos, BigDecimal precoMin, BigDecimal precoMax);

    List<Imovel> findByLocador_Id(Long id);

}
