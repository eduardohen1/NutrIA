package es.minsait.NutrIA.service;

import dev.langchain4j.model.chat.ChatLanguageModel;
import dev.langchain4j.model.openai.OpenAiChatModel;
import dev.langchain4j.service.AiServices;
import es.minsait.NutrIA.model.Receita;
import es.minsait.NutrIA.repository.IngredientesRepository;
import es.minsait.NutrIA.service.interfaces.NutricioalAIService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class NutricionalService {

    private final IngredientesRepository ingredientesRepository;
    private final NutricioalAIService nutricioalAIService;

    public NutricionalService(
            IngredientesRepository ingredientesRepository,
            @Value("${langchain4j.open-ai.chat-model.api-key:}") String apiKey,
            @Value("${langchain4j.open-ai.chat-model-name:gpt-4o-2024-08-06}") String modelName
    ){
        this.ingredientesRepository = ingredientesRepository;
        if(apiKey == null || apiKey.isEmpty() || apiKey.equals("${OPENAI_API_KEY}")){
            this.nutricioalAIService = ingredients ->
                "{\"calorias\": 250, \"carboidratos\": 30, \"proteinas\": 10, \"gorduras\": 8}";
        } else {
            ChatLanguageModel chatLanguageModel = OpenAiChatModel.builder()
                    .apiKey(apiKey)
                    .modelName(modelName)
                    .build();

            this.nutricioalAIService = AiServices.builder(NutricioalAIService.class)
                    .chatLanguageModel(chatLanguageModel)
                    .build();
        }
    }

    //Calcular valores nutricionais por receita
    public void calcularNutricionalPorReceita(Receita receita){
        Map<String, Double>  totalNutricional = new HashMap<>();
        totalNutricional.put("calorias", 0.0);
        totalNutricional.put("carboidratos", 0.0);
        totalNutricional.put("proteinas", 0.0);
        totalNutricional.put("gorduras", 0.0);

        List<String> ingredientes = receita.getIngredientes();
        for(String ingredienteText : ingredientes){
            //Extrair nome do ingrediente e quantidade
            Map<String, Object> ingredienteInfo = parseIngrediente(ingredienteText);
            String ingredienteNome = (String) ingredienteInfo.get("nome");
            double quantidade = (double) ingredienteInfo.get("quantidade");

            //Buscar ingrediente no banco de dados
            var ingredienteOpt = ingredientesRepository.findByNomeContainingIgnoreCase(ingredienteNome);
            if(ingredienteOpt.isPresent()){
                var ingrediente = ingredienteOpt.get();
                //Calcular valores nutricionais proporcionais a quantidade
                double fator = quantidade / 100.0; //Assumindo que os valores nutricionais estao por 100g/ml
                totalNutricional.put("calorias", totalNutricional.get("calorias") + ingrediente.getCalorias() * fator);
                totalNutricional.put("carboidratos", totalNutricional.get("carboidratos") + ingrediente.getCarboidratos() * fator);
                totalNutricional.put("proteinas", totalNutricional.get("proteinas") + ingrediente.getProteinas() * fator);
                totalNutricional.put("gorduras", totalNutricional.get("gorduras") + ingrediente.getGorduras() * fator);
            } else {
                try {
                    //Se o ingrediente nao for encontrado, usar AI para estimar valores nutricionais
                    String ingredientesStr = String.join(", ", ingredientes);
                    String aiResponse = nutricioalAIService.calcularNutricional(ingredientesStr);

                    Pattern pattern = Pattern.compile("\"(calorias|carboidratos|proteinas|gorduras)\"\\s*:\\s*(\\d+(\\.\\d+)?)");
                    Matcher matcher = pattern.matcher(aiResponse);

                    while (matcher.find()) {
                        String key = matcher.group(1);
                        double value = Double.parseDouble(matcher.group(2));
                        totalNutricional.put(key, totalNutricional.get(key) + value);
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }

        //atualizar a receita com os valores nutricionais totais
        receita.setCalorias(totalNutricional.get("calorias"));
        receita.setCarboidratos(totalNutricional.get("carboidratos"));
        receita.setProteinas(totalNutricional.get("proteinas"));
        receita.setGorduras(totalNutricional.get("gorduras"));

    }

    private Map<String, Object> parseIngrediente(String ingredienteStr){
        Map<String, Object> resultado = new HashMap<>();
        Pattern pattern = Pattern.compile("(\\d+)\\s*g\\s+de\\s+(.+)");
        Matcher matcher = pattern.matcher(ingredienteStr);

        if(matcher.find()){
            double quantidade = Double.parseDouble(matcher.group(1));
            String nome = matcher.group(2).trim();
            resultado.put("quantidade", quantidade);
            resultado.put("nome", nome);
        } else {
            //Se nao conseguir parsear, assumir 100g
            resultado.put("quantidade", 100.0);
            resultado.put("nome", ingredienteStr.trim());
        }
        return resultado;
    }

}
