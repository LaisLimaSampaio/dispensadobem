package com.example.dispensadobem.repository;

import com.example.dispensadobem.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {
    List<Produto> findByEstabelecimentoId(Long estabelecimentoId);

}
