package com.aluizio.sacola.service;

import com.aluizio.sacola.model.Item;
import com.aluizio.sacola.model.Sacola;
import com.aluizio.sacola.resources.dto.ItemDto;

public interface SacolaService {
    Item incluirItemNaSacola(ItemDto itemDto);
    Sacola verSacola(Long id);
    Sacola fecharSacola(Long id, int formaPagamento);

}
