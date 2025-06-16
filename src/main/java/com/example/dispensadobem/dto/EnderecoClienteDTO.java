package com.example.dispensadobem.dto;

import lombok.Data;

@Data
public class EnderecoClienteDTO {
    private String rua;
    private String numero;
    private String complemento;
    private String bairro;
    private String cidade;
    private String estado;
    private String cep;
}
