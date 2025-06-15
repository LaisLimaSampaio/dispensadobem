package com.example.dispensadobem.repository;

import com.example.dispensadobem.model.Avaliacao;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AvaliacaoRepository extends JpaRepository<Avaliacao, Long> {
    List<Avaliacao> findByEstabelecimentoId(Long estabelecimentoId);
    List<Avaliacao> findByClienteId(Long clienteId);
}