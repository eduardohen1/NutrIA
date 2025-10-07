# NutrIA - Sistema de Gerenciamento de Receitas com Análise Nutricional Inteligente

NutrIA é uma aplicação **Spring Boot** para gerenciamento de receitas que utiliza **inteligência artificial** para calcular valores nutricionais e classificar dietas de forma automatizada. A aplicação combina um banco de dados de ingredientes com análise de IA para fornecer informações nutricionais precisas mesmo quando os ingredientes não estão cadastrados.

## 🚀 Funcionalidades Principais
- **Cadastro e gerenciamento de receitas** - Armazene suas receitas favoritas com ingredientes e instruções.
- **Cálculo automático de valores nutricionais** - Baseado em banco de dados de ingredientes ou IA.
- **Classificação de dietas** - Verifica automaticamente se a receita é compatível com:
    - Dieta vegetariana
    - Dieta vegana
    - Alimentação sem glúten
    - Alimentação sem lactose
    - Dieta low-carb
    - Dieta cetogênica
    - Dieta paleo

## 🛠 Tecnologias Utilizadas
- **Spring Boot** - Framework Java para desenvolvimento de aplicações
- **JPA/Hibernate** - Persistência de dados
- **H2 Database** - Banco de dados em memória
- **Langchain4j** - Integração com IA para processamento de linguagem natural
- **OpenAI API** - Motor de IA para análise de ingredientes e cálculos nutricionais

## 📂 Estrutura do Projeto
O projeto segue uma arquitetura em camadas:
- **Model** - Entidades JPA (`Recipe`, `Ingredient`)
- **Repository** - Interfaces para acesso a dados
- **Service** - Lógica de negócio e integrações com IA
- **Controller** - APIs REST
- **DTO** - Objetos de transferência de dados

## ▶️ Como Executar

### Pré-requisitos
- Java 17+
- Maven
- Chave de API da OpenAI (opcional)

### Configuração
1. Clone o repositório
2. Configure sua chave de API da OpenAI no arquivo `application.properties` ou como variável de ambiente:
   ```bash
   export OPENAI_API_KEY=sua-chave-aqui
   ```
3. Execute a aplicação:
   ```bash
   mvn spring-boot:run
   ```

> **Nota:** A aplicação funciona em modo de desenvolvimento sem a chave da API, retornando valores nutricionais simulados.

## 📡 Exemplos de Uso da API

### Criar uma nova receita
```bash
curl AQUI
```

### Calcular valores nutricionais de uma receita
```bash
curl AQUI
```

### Obter valores nutricionais de uma receita
```bash
curl AQUI
```

### Classificar dieta de uma receita
```bash
curl AQUI
```

## 🗄️ Banco de Dados
A aplicação utiliza um banco de dados **H2** em memória inicializado com:
- Mais de 20 ingredientes básicos com valores nutricionais
- Receitas de exemplo

O console H2 está disponível em: [http://localhost:8080/h2-console](http://localhost:8080/h2-console)

- **URL:** `jdbc:h2:mem:nutria`
- **Usuário:** `sa`
- **Senha:** (vazia)

## 🧠 Como a IA é utilizada no projeto
- **Cálculo de valores nutricionais:** Quando os ingredientes não estão no banco de dados, a IA analisa a lista de ingredientes e estima os valores nutricionais.
- **Classificação de dietas:** A IA analisa os ingredientes para determinar a compatibilidade com diferentes tipos de dietas.

## 📜 Licença
Este projeto é licenciado sob a licença MIT - consulte o arquivo LICENSE para mais detalhes.

## 🤝 Contribuições
Contribuições são bem-vindas! Sinta-se à vontade para abrir issues ou enviar pull requests.

---
Desenvolvido como parte do projeto **NutrIA - Inteligência Artificial aplicada à nutrição**.
