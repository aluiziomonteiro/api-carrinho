package com.aluizio.sacola.resources.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

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
