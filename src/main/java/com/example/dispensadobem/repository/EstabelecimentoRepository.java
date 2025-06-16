package com.example.dispensadobem.repository;

import com.example.dispensadobem.model.Estabelecimento;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EstabelecimentoRepository extends JpaRepository<Estabelecimento, Long> {
    Optional<Estabelecimento> findByCnpj(String cnpj);
    Optional<Estabelecimento> findByEmail(String email);
    Optional<Estabelecimento> findByEmailAndSenha(String email, String senha);

}
