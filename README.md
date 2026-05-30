# 🧊 Geladeira Inteligente — API Geradora de Receitas com IA

![Java](https://img.shields.io/badge/Java-17-orange?style=for-the-badge&logo=java)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.x-brightgreen?style=for-the-badge&logo=spring)
![H2](https://img.shields.io/badge/H2-Database-blue?style=for-the-badge&logo=h2)
![Gemini](https://img.shields.io/badge/Gemini_AI-API-8E75B2?style=for-the-badge&logo=google)
![Lombok](https://img.shields.io/badge/Lombok-red?style=for-the-badge)

Uma API RESTful inteligente desenvolvida em Java e Spring Boot que transforma os ingredientes da sua geladeira em receitas criativas. O grande diferencial do projeto é a **integração com a API do Google Gemini**, que analisa os itens cadastrados e gera sugestões de receitas personalizadas com base no que você tem disponível.

## 🎯 Sobre o Projeto

O objetivo desta aplicação é fornecer um back-end completo para o gerenciamento de ingredientes em uma geladeira virtual e, a partir deles, acionar uma IA generativa para sugerir receitas viáveis. O usuário cadastra, consulta e remove itens do estoque; a API comunica esses dados ao **Google Gemini** e retorna receitas detalhadas de forma automática.

## 🚀 Tecnologias Utilizadas

* **Linguagem:** Java 17
* **Framework:** Spring Boot
* **Banco de Dados:** H2 (em memória, com console web disponível)
* **Migrations:** Flyway
* **Comunicação Reativa:** Spring WebFlux (WebClient para chamadas à API do Gemini)
* **API Web:** Spring Web MVC
* **Acesso a Dados:** Spring Data JPA / Hibernate
* **IA Generativa:** Google Gemini API
* **Utilitários:** Lombok

## 🧠 Arquitetura e Padrões Aplicados

Este projeto foi construído com foco em boas práticas de engenharia de software:

* **Separação de Responsabilidades (Layered Architecture):** Divisão clara entre Controllers, Services e Repositories.
* **Padrão DTO (Data Transfer Object):** Utilização de `Records` do Java para expor e receber dados, blindando as entidades de banco de dados do acesso externo.
* **Comunicação Reativa com WebClient:** Uso do `WebClient` do Spring WebFlux para realizar chamadas assíncronas e não-bloqueantes à API do Gemini, garantindo eficiência nas integrações externas.
* **Migrations Versionadas com Flyway:** Controle do schema do banco de dados de forma incremental e rastreável.
* **Tratamento Global de Exceções:** Implementação de `@RestControllerAdvice` para padronizar respostas de erro da API, garantindo retornos amigáveis para erros de validação (400) e recursos não encontrados (404).
* **Console H2:** Banco em memória com interface web acessível em desenvolvimento para inspeção rápida dos dados.

## 🛠️ Como Executar o Projeto Localmente

**Pré-requisitos:** Java 17 (ou superior) instalado, Maven e uma chave de API válida do Google Gemini.

1. Clone o repositório:
   ```bash
   git clone https://github.com/seu-usuario/geladeira-inteligente.git
   ```

2. Configure sua chave da API do Gemini no arquivo `src/main/resources/application.properties`:
   ```properties
   gemini.api.key=SUA_CHAVE_AQUI
   ```

3. Execute a aplicação via Maven:
   ```bash
   ./mvnw spring-boot:run
   ```

> **Nota:** O banco H2 é criado automaticamente em memória a cada execução. O console web está disponível em `http://localhost:8080/h2-console` — use a JDBC URL `jdbc:h2:mem:testdb` para conectar.

## 📖 Documentação dos Endpoints

A URL base da aplicação rodando localmente é `http://localhost:8080/api`.

### 🥦 Ingredientes

| Método | Rota | Descrição |
|---|---|---|
| `GET` | `/ingredientes` | Lista todos os ingredientes cadastrados na geladeira. |
| `GET` | `/ingredientes/{id}` | Busca um ingrediente específico pelo seu ID. |
| `POST` | `/ingredientes` | Adiciona um novo ingrediente à geladeira. |
| `DELETE` | `/ingredientes/{id}` | Remove um ingrediente da geladeira. |

### 🤖 Receitas com IA

| Método | Rota | Descrição |
|---|---|---|
| `GET` | `/receitas/gerar` | Envia os ingredientes cadastrados ao Gemini e retorna receitas sugeridas. |

### Exemplo de Payload: Adicionar Ingrediente (POST)

```json
{
  "nome": "Frango",
  "quantidade": 2,
  "unidade": "unidades",
  "validade": "2025-06-15"
}
```

### Exemplo de Resposta: Gerar Receitas (GET)

```json
{
  "receitas": [
    {
      "nome": "Frango ao Alho e Azeite",
      "ingredientesUsados": ["Frango", "Alho", "Azeite"],
      "modoPreparo": "Tempere o frango com sal, pimenta e alho amassado. Frite em azeite quente por 20 minutos, virando na metade do tempo.",
      "tempoPreparo": "30 minutos"
    }
  ]
}
```
