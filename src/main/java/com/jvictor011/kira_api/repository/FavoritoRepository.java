package com.jvictor011.kira_api.repository;

import com.jvictor011.kira_api.model.entity.Favorito;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FavoritoRepository extends JpaRepository<Favorito, Long> {
    List<Favorito> findByUsuario_Id(Long usuarioId);
}
