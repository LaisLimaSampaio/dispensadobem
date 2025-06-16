package com.example.dispensadobem.dto;

import lombok.Data;

@Data
public class ClienteDTO {
    private String nome;
    private String email;
    private String senha;
    private String telefone;
    private EnderecoClienteDTO endereco;
}