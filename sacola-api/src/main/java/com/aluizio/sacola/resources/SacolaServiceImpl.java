package com.aluizio.sacola.resources;

import com.aluizio.sacola.enumeration.FormaPagamento;
import com.aluizio.sacola.exceptions.*;
import com.aluizio.sacola.model.Item;
import com.aluizio.sacola.model.Restaurante;
import com.aluizio.sacola.model.Sacola;
import com.aluizio.sacola.repository.ItemRepository;
import com.aluizio.sacola.repository.ProdutoRepository;
import com.aluizio.sacola.repository.SacolaRepository;
import com.aluizio.sacola.resources.dto.ItemDto;
import com.aluizio.sacola.service.SacolaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SacolaServiceImpl implements SacolaService {
    private final SacolaRepository sacolaRepository;
    private final ProdutoRepository produtoRepository;
    private final ItemRepository itemRepository;

    @Override
    public void excluirItemDaSacola(Long idItem, Long sacolaId) {
        Sacola sacola = verSacola(sacolaId);

        if(sacola.isFechada()){
            throw new SacolaFechadaException("Esta sacola está fechada!");
        }

        List<Item> itensDaSacola = sacola.getItens();

        if(itensDaSacola.isEmpty()){
            throw new SacolaVaziaException("Esta sacola está vazia!");
        }

        Item itemParaSerRemovido = itemRepository.findById(idItem).orElseThrow(
                () -> new ItemNaoExisteException("Esse item não existe")
        );
        itensDaSacola.remove(itemParaSerRemovido);
    }

    @Override
    public Item incluirItemNaSacola(ItemDto itemDto) {
       Sacola sacola = verSacola(itemDto.getIdSacola());

       if(sacola.isFechada()){
           throw new SacolaFechadaException("Esta sacola está fechada!");
       }

       Item itemParaSerInserido = Item.builder()
               .quantidade(itemDto.getQuantidade())
               .sacola(sacola)
               .produto(produtoRepository.findById(itemDto.getProdutoId()).orElseThrow(
                       () -> new ProdutoNaoExisteException("Esse produto não existe")
               ))
               .build();

       List<Item> itensDaSacola = sacola.getItens();

       if(itensDaSacola.isEmpty()){
           itensDaSacola.add(itemParaSerInserido);
       }else{
           Restaurante restauranteAtual = itensDaSacola.get(0).getProduto().getRestaurante();
           Restaurante restauranteDoItemParaAdicionar = itemParaSerInserido.getProduto().getRestaurante();

           if(restauranteAtual.equals(restauranteDoItemParaAdicionar)){
               itensDaSacola.add(itemParaSerInserido);
           } else {
               throw new RestauranteDiferenteException(
                       "Não é possível adicionar produtos de restaurantes diferentes. Feche a sacola ou esvazie."
               );
           }
       }
       List<Double> valorDosItens = new ArrayList<>();
       for(Item itemDaSacola : itensDaSacola){
           double valorTotalItem = itemDaSacola.getProduto().getValorUnitario() * itemDaSacola.getQuantidade();
           valorDosItens.add(valorTotalItem);
       }

       double valorTotalSacola = valorDosItens.stream().mapToDouble(valorTotalDeCadaItem -> valorTotalDeCadaItem).sum();

       sacola.setValorTotal(valorTotalSacola);
       sacolaRepository.save(sacola);
       return itemParaSerInserido;
    }

    @Override
    public Sacola verSacola(Long id) {
        return sacolaRepository.findById(id).orElseThrow(
                () -> new SacolaNaoEncontradaException("Essa sacola não existe")
        );
    }

    @Override
    public Sacola fecharSacola(Long id, int numeroFormaPagamento) {
        Sacola sacola = verSacola(id);

        if(sacola.getItens().isEmpty()){
            throw new SacolaVaziaException("Inclua itens na sacola!");
        }

        FormaPagamento formaPagamento =
                numeroFormaPagamento == 0 ? FormaPagamento.DINHEIRO : FormaPagamento.MAQUINETA;

        sacola.setFormaPagamento(formaPagamento);
        sacola.setFechada(true);
        return sacolaRepository.save(sacola);
    }

    @Override
    public void excluirSacola(Long id) {
        if(!sacolaRepository.existsById(id)){
            throw new SacolaNaoEncontradaException("Essa sacola não existe!");
        }
        sacolaRepository.deleteById(id);
    }
}
