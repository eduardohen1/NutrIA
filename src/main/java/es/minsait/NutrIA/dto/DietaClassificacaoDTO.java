package es.minsait.NutrIA.dto;

import java.util.Map;

public record DietaClassificacaoDTO(Long receitaId,
                                    String titulo,
                                    boolean vegetariana,
                                    boolean vegana,
                                    boolean semGluten,
                                    boolean semLactose,
                                    boolean lowCarb,
                                    boolean cetogenica,
                                    boolean paleo) {
    public static DietaClassificacaoDTO fromMap(Long recitaId, String titulo, java.util.Map<String, Boolean> classificacao) {
        return new DietaClassificacaoDTO(
                recitaId,
                titulo,
                classificacao.getOrDefault("vegetariana", false),
                classificacao.getOrDefault("vegana", false),
                classificacao.getOrDefault("sem_gluten", false),
                classificacao.getOrDefault("sem_lactose", false),
                classificacao.getOrDefault("low_carb", false),
                classificacao.getOrDefault("cetogenica", false),
                classificacao.getOrDefault("paleo", false)
        );
    }
    public Map<String, Object> toMap() {
        Map<String, Object> map = new java.util.HashMap<>();
        map.put("recitaId", receitaId);
        map.put("titulo", titulo);
        map.put("vegetariana", vegetariana);
        map.put("vegana", vegana);
        map.put("semGluten", semGluten);
        map.put("semLactose", semLactose);
        map.put("lowCarb", lowCarb);
        map.put("cetogenica", cetogenica);
        map.put("paleo", paleo);
        return map;
    }
}
