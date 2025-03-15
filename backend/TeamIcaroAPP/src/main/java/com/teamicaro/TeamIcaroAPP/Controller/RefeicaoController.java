package com.teamicaro.TeamIcaroAPP.Controller;

import com.teamicaro.TeamIcaroAPP.Model.Refeicao;
import com.teamicaro.TeamIcaroAPP.Service.RefeicaoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/refeicoes")
public class RefeicaoController {

    private final RefeicaoService refeicaoService;

    public RefeicaoController(RefeicaoService refeicaoService) {
        this.refeicaoService = refeicaoService;
    }

    @PostMapping
    public ResponseEntity<Refeicao> criarRefeicao(@RequestBody Refeicao refeicao) {
        return ResponseEntity.ok(refeicaoService.criarRefeicao(refeicao));
    }

    @GetMapping("/dieta/{dietaId}")
    public ResponseEntity<List<Refeicao>> listarRefeicoes(@PathVariable Long dietaId) {
        return ResponseEntity.ok(refeicaoService.listarRefeicoesPorDieta(dietaId));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirRefeicao(@PathVariable Long id) {
        refeicaoService.excluirRefeicao(id);
        return ResponseEntity.noContent().build();
    }
}
