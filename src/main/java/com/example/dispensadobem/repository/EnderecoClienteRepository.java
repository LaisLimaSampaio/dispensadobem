package com.example.dispensadobem.repository;

import com.example.dispensadobem.model.EnderecoCliente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EnderecoClienteRepository extends JpaRepository<EnderecoCliente, Long> {
    Optional<EnderecoCliente> findByClienteId(Long clienteId);
}
