package es.minsait.NutrIA.service;

import es.minsait.NutrIA.dto.DietaClassificacaoDTO;
import es.minsait.NutrIA.model.Receita;
import es.minsait.NutrIA.repository.ReceitaRepository;
import es.minsait.NutrIA.service.interfaces.NutricioalAIService;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;

@Service
public class ReceitaService {

    private final ReceitaRepository receitaRepository;
    private final NutricionalService nutricionalService;
    private final DietaService dietaService;

    public ReceitaService(ReceitaRepository receitaRepository, NutricionalService nutricionalService, DietaService dietaService) {
        this.receitaRepository = receitaRepository;
        this.nutricionalService = nutricionalService;
        this.dietaService = dietaService;
    }

    public Receita save(Receita receita) {
        //validar argumentos se necessario
        try{
            if(receita.getTitulo() == null || receita.getTitulo().isEmpty()) {
                throw new IllegalArgumentException("Nome da receita nao pode ser vazio");
            }
            if(receita.getIngredientes() == null || receita.getIngredientes().isEmpty()) {
                throw new IllegalArgumentException("Ingredientes da receita nao podem ser vazios");
            }
            if(receita.getInstrucoes() == null || receita.getInstrucoes().isEmpty()) {
                throw new IllegalArgumentException("Modo de preparo da receita nao pode ser vazio");
            }
            if(receita.getPorcoes() < 1) {
                throw new IllegalArgumentException("Porcoes da receita devem ser maiores que zero");
            }
            return receitaRepository.save(receita);
        } catch (IllegalArgumentException e) {
            System.err.println("Erro ao salvar receita: " + e.getMessage());
            throw new RuntimeException("Erro ao salvar receita: " + e.getMessage());
        }

    }

    //buscar por id
    public Receita findById(Long id) {
        return receitaRepository.findById(id).orElse(null);
    }

    //deletar por id
    public void deleteById(Long id) {
        receitaRepository.deleteById(id);
    }

    //buscar todas as receitas
    public java.util.List<Receita> findAll() {
        return receitaRepository.findAll();
    }

    //atualizar receita
    public Receita update(Long id, Receita updatedReceita) {
        return receitaRepository.findById(id).map(receita -> {
            receita.setTitulo(updatedReceita.getTitulo());
            receita.setIngredientes(updatedReceita.getIngredientes());
            receita.setInstrucoes(updatedReceita.getInstrucoes());
            receita.setPorcoes(updatedReceita.getPorcoes());
            return receitaRepository.save(receita);
        }).orElse(null);
    }

    public Optional<Receita> calcularNutricionalPorReceita(Long id){
        return receitaRepository.findById(id).map(
                receita -> {
                    nutricionalService.calcularNutricionalPorReceita(receita);
                    return receitaRepository.save(receita);
                }
        );
    }

    public Optional<DietaClassificacaoDTO> classificarReceitaDieta(Long id){
        return receitaRepository.findById(id).map(
                receita -> {
                    Map<String, Boolean> classificacao = dietaService.classificarReceita(receita);
                    return DietaClassificacaoDTO.fromMap(receita.getId(),
                            receita.getTitulo(), classificacao);
                }
        );
    }

}
