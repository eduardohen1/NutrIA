package es.minsait.NutrIA.service.interfaces;

import dev.langchain4j.service.SystemMessage;
import dev.langchain4j.service.UserMessage;

public interface NutricioalAIService {

    @SystemMessage(
            "Você é um assistente especializado em nutrição. "
                    + "Analise os ingredientes e devolva os valores nutricionais aproximados para 100g em formato JSON: "
                    + "{\"calorias\": X, \"carboidratos\": X, \"proteinas\": X, \"gorduras\": X}. "
                    + "Apenas retorne o JSON sem texto adicional."
    )
    String calcularNutricional(@UserMessage String ingredientes);

}
