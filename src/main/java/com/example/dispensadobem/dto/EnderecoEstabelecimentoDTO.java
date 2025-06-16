package com.example.dispensadobem.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EnderecoEstabelecimentoDTO {
    private String rua;
    private String numero;
    private String complemento;
    private String bairro;
    private String cidade;
    private String estado;
    private String cep;
}
