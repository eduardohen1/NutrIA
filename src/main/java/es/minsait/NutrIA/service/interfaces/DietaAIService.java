package es.minsait.NutrIA.service.interfaces;

import dev.langchain4j.service.SystemMessage;
import dev.langchain4j.service.UserMessage;

public interface DietaAIService {
    @SystemMessage(
            "Você é um assistente especializado em nutrição. " +
                    "Analise os ingredientes da receita e classifique se ela é adequada " +
                    "para as seguintes dietas: vegetariana, vegana, sem glúten, sem lactose, low-carb, " +
                    "cetogênica, paleo. Responda apenas em formato JSON: " +
                    "{\"vegetariana\": true|false, \"vegana\": true|false, " +
                    "\"sem_gluten\": true|false, \"sem_lactose\": true|false, \"low_carb\": true|false, " +
                    "\"cetogenica\": true|false, \"paleo\": true|false}. Não inclua texto adicional."
    )
    String classificarDieta(@UserMessage String ingredientes);
}
