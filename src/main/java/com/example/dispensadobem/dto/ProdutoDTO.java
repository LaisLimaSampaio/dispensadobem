package com.example.dispensadobem.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
public class ProdutoDTO {

    private EstabelecimentoIdOnly estabelecimento;
    private CategoriaProdutoIdOnly categoria;
    private String nome;
    private String descricao;
    private String fotoUrl;
    private LocalDate dataValidade;
    private BigDecimal precoOriginal;
    private BigDecimal precoDesconto;

    @Getter
    @Setter
    public static class EstabelecimentoIdOnly {
        private Long id;
    }

    @Getter
    @Setter
    public static class CategoriaProdutoIdOnly {
        private Long id;
    }
}