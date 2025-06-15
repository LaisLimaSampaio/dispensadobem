package com.example.dispensadobem.repository;

import com.example.dispensadobem.model.EnderecoEstabelecimento;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EnderecoEstabelecimentoRepository extends JpaRepository<EnderecoEstabelecimento, Long> {
    Optional<EnderecoEstabelecimento> findByEstabelecimentoId(Long estabelecimentoId);
}