package com.jvictor011.kira_api.repository;

import com.jvictor011.kira_api.model.entity.Arquivo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArquivoRepository extends JpaRepository<Arquivo, Long> {
}
