package com.teamicaro.TeamIcaroAPP.Controller;

import com.teamicaro.TeamIcaroAPP.Model.Alimento;
import com.teamicaro.TeamIcaroAPP.Service.AlimentoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/alimentos")
public class AlimentoController {

    private final AlimentoService alimentoService;

    public AlimentoController(AlimentoService alimentoService) {
        this.alimentoService = alimentoService;
    }

    @PostMapping
    public ResponseEntity<Alimento> adicionarAlimento(@RequestBody Alimento alimento) {
        return ResponseEntity.ok(alimentoService.adicionarAlimento(alimento));
    }

    @GetMapping("/refeicao/{refeicaoId}")
    public ResponseEntity<List<Alimento>> listarAlimentos(@PathVariable Long refeicaoId) {
        return ResponseEntity.ok(alimentoService.listarAlimentosPorRefeicao(refeicaoId));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirAlimento(@PathVariable Long id) {
        alimentoService.excluirAlimento(id);
        return ResponseEntity.noContent().build();
    }
}
