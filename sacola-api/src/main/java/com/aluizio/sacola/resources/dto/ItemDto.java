package com.aluizio.sacola.resources.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;

/**
Serve para modelar o nosso Json
**/
@Builder
@AllArgsConstructor
@Data
@NoArgsConstructor
@Embeddable
public class ItemDto {
    private Long produtoId;
    private int quantidade;
    private Long idSacola;
}
