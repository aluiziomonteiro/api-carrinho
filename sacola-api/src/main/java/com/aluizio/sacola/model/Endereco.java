package com.aluizio.sacola.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;

@Builder
@AllArgsConstructor
@Data
@NoArgsConstructor
@Embeddable
public class Endereco {
    private String cep;
    private String complemento;
}
