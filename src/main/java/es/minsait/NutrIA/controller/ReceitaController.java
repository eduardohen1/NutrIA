package es.minsait.NutrIA.controller;

import es.minsait.NutrIA.model.Receita;
import es.minsait.NutrIA.service.ReceitaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/receitas")
public class ReceitaController {

    private final ReceitaService receitaService;

    public ReceitaController(ReceitaService receitaService) {
        this.receitaService = receitaService;
    }

    @GetMapping
    public ResponseEntity<List<Receita>> obterTodasReceitas() {
        List<Receita> receitas = receitaService.findAll();
        if (receitas.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(receitas);
    }

    @PostMapping
    public ResponseEntity<Receita> salvarReceita(@RequestBody Receita receita) {
        Receita receitaSalva = receitaService.save(receita);
        return ResponseEntity.ok(receitaSalva);
    }

    @GetMapping("/{id}/calcula-nutricional")
    public ResponseEntity<Receita> calcularInformacoesNutricionais(@PathVariable Long id) {
        return receitaService.calcularNutricionalPorReceita(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/{id}/dieta-classificacao")
    public ResponseEntity<Map<String, Object>> getReceitaDietaClassificacao(@PathVariable Long id) {
        return receitaService.classificarReceitaDieta(id)
                .map(dietaClassificacaoDTO -> ResponseEntity.ok(dietaClassificacaoDTO.toMap()))
                .orElse(ResponseEntity.notFound().build());
    }

}
