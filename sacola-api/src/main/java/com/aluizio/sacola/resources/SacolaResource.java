package com.aluizio.sacola.resources;

import com.aluizio.sacola.model.Item;
import com.aluizio.sacola.model.Sacola;
import com.aluizio.sacola.resources.dto.ItemDto;
import com.aluizio.sacola.service.SacolaService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@Api(value = "/ifood-dev-week/sacola")
@RestController
@RequiredArgsConstructor
@RequestMapping("/ifood-dev-week/sacola") //Quando chegar uma requisição na url /sacolas
public class SacolaResource {

    private final SacolaService sacolaService;

    @PostMapping    // Do tipo Post
    public Item incluirItemNaSacola(@RequestBody ItemDto itemDto){
        return sacolaService.incluirItemNaSacola(itemDto); // envie para o serviço processar
    }
    @GetMapping("/{id}")
    public Sacola verSacola(@PathVariable("id") Long id){
        return sacolaService.verSacola(id);
    }
    @PatchMapping("/fecharSacola/{sacolaId}")// atualiza parte de uma coisa. O put atualiza tudo
    public Sacola fecharSacola(@PathVariable("sacolaId") Long sacolaId,
                               @RequestParam("formaPagamento") int formaPagamento){
        return sacolaService.fecharSacola(sacolaId, formaPagamento);
    }
}
