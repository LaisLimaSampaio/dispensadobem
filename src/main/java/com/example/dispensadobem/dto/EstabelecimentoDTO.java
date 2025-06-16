package com.example.dispensadobem.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class EstabelecimentoDTO {
    private String nomeFantasia;
    private String cnpj;
    private String email;
    private String senha;
    private String telefone;
    private String horarioFuncionamento;
    private Boolean ofereceEntrega;
    private BigDecimal taxaEntrega;
    private BigDecimal entregaGratisAcima;
    private BigDecimal distanciaMaxEntregaKm;
    private CategoriaIdDTO categoria;
    private EnderecoEstabelecimentoDTO endereco;
}