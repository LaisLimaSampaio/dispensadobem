package com.example.dispensadobem.repository;

import com.example.dispensadobem.model.Favorito;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FavoritoRepository extends JpaRepository<Favorito, Long> {
    List<Favorito> findByClienteId(Long clienteId);
    Optional<Favorito> findByClienteIdAndEstabelecimentoId(Long clienteId, Long estabelecimentoId);
}

