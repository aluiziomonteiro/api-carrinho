package com.aluizio.sacola.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@Data
@NoArgsConstructor
@Embeddable
public class Endereco {
    private String cep;
    private String complemento;
}
