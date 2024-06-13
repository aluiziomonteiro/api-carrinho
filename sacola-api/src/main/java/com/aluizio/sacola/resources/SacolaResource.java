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
@RequestMapping("/ifood-dev-week/sacola")
public class SacolaResource {

    private final SacolaService sacolaService;

    @PostMapping
    public Item incluirItemNaSacola(@RequestBody ItemDto itemDto){
        return sacolaService.incluirItemNaSacola(itemDto);
    }
    @GetMapping("/{id}")
    public Sacola verSacola(@PathVariable("id") Long id){
        return sacolaService.verSacola(id);
    }

    @DeleteMapping("excluirSacola/{id}")
    public void excluirSacola(@PathVariable("id") Long id){
        sacolaService.excluirSacola(id);
    }

    @DeleteMapping("/excluirItemDaSacola/{itemId}")
    public void excluirItemDaSacola(@PathVariable("id") Long itemId,
                                    @RequestParam("sacolaId") Long sacolaId){
        sacolaService.excluirItemDaSacola(itemId, sacolaId);
    }

    @PatchMapping("/fecharSacola/{sacolaId}")
    public Sacola fecharSacola(@PathVariable("sacolaId") Long sacolaId,
                               @RequestParam("formaPagamento") int formaPagamento){
        return sacolaService.fecharSacola(sacolaId, formaPagamento);
    }
}
