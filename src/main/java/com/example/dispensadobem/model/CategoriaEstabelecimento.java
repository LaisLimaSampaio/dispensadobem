package com.example.dispensadobem.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "categoria_estabelecimento")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoriaEstabelecimento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String nome;
}

