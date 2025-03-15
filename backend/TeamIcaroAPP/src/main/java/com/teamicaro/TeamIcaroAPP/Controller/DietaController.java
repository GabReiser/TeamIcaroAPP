package com.teamicaro.TeamIcaroAPP.Controller;
import com.teamicaro.TeamIcaroAPP.Model.Dieta;
import com.teamicaro.TeamIcaroAPP.Service.DietaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/dietas")
public class DietaController {

    private final DietaService dietaService;

    public DietaController(DietaService dietaService) {
        this.dietaService = dietaService;
    }

    @PostMapping
    public ResponseEntity<Dieta> criarDieta(@RequestBody Dieta dieta) {
        return ResponseEntity.ok(dietaService.salvarDieta(dieta));
    }

    @GetMapping("/usuario/{userId}")
    public ResponseEntity<List<Dieta>> listarDietas(@PathVariable Long userId) {
        return ResponseEntity.ok(dietaService.buscarDietasPorUsuario(userId));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirDieta(@PathVariable Long id) {
        dietaService.excluirDieta(id);
        return ResponseEntity.noContent().build();
    }
}