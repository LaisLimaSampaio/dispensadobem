package com.example.dispensadobem.repository;
import com.example.dispensadobem.model.CategoriaEstabelecimento;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoriaEstabelecimentoRepository extends JpaRepository<CategoriaEstabelecimento, Long> {
    Optional<CategoriaEstabelecimento> findByNome(String nome);
}
