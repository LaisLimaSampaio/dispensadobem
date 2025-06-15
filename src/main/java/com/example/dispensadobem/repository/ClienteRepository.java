package com.example.dispensadobem.repository;

import com.example.dispensadobem.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    Optional<Cliente> findByEmail(String email);


    Cliente findByEmailAndSenha(String email, String senha);


}
