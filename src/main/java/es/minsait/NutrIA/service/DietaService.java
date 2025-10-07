package es.minsait.NutrIA.service;

import dev.langchain4j.model.chat.ChatLanguageModel;
import dev.langchain4j.model.openai.OpenAiChatModel;
import dev.langchain4j.service.AiServices;
import es.minsait.NutrIA.model.Receita;
import es.minsait.NutrIA.service.interfaces.DietaAIService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class DietaService {

    private final DietaAIService dietaAIService;

    public DietaService(@Value("${langchain4j.open-ai.chat-model.api-key:}") String apiKey,
                        @Value("${langchain4j.open-ai.chat-model-name:gpt-4o-2024-08-06}") String modelName){
        if(apiKey == null || apiKey.isEmpty() || apiKey.equals("${OPENAI_API_KEY}")){
            this.dietaAIService = ingredients ->
                    "{\"vegetariana\": true, \"vegana\": false, \"sem_gluten\": false, \"sem_lactose\": false, " +
                            "\"low_carb\": false, \"cetogenica\": false, \"paleo\": false}";
        } else {
            ChatLanguageModel chatLanguageModel = OpenAiChatModel.builder()
                    .apiKey(apiKey)
                    .modelName(modelName)
                    .build();
            this.dietaAIService = AiServices.builder(DietaAIService.class)
                    .chatLanguageModel(chatLanguageModel)
                    .build();
        }

    }

    public Map<String, Boolean> classificarReceita(Receita receita){
        String ingredientesText = String.join(", ", receita.getIngredientes());
        String response = dietaAIService.classificarDieta(ingredientesText);
        return parseDietClassification(response);
    }

    private Map<String, Boolean> parseDietClassification(String jsonResponse){
        Map<String, Boolean> classifications = new java.util.HashMap<>();
        java.util.regex.Pattern pattern = java.util.regex.Pattern.compile("\"(\\w+)\"\\s*:\\s*(true|false)");
        java.util.regex.Matcher matcher = pattern.matcher(jsonResponse);

        while (matcher.find()) {
            String dietType = matcher.group(1);
            boolean isCompatible = Boolean.parseBoolean(matcher.group(2));
            classifications.put(dietType, isCompatible);
        }
        return classifications;
    }

}
