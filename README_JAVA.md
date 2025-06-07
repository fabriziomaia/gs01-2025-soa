## Instruções de Execução do Projeto Java com Maven

Este documento fornece as instruções necessárias para compilar e executar o projeto Java com Maven.

### Pré-requisitos

Certifique-se de ter os seguintes softwares instalados em sua máquina:

*   **Java Development Kit (JDK) 17 ou superior**: Você pode baixar o JDK a partir do site oficial da Oracle ou usar um gerenciador de pacotes como o `apt` (para sistemas baseados em Debian/Ubuntu) ou `brew` (para macOS).
    ```bash
    sudo apt update
    sudo apt install -y openjdk-17-jdk
    ```

*   **Apache Maven 3.6.0 ou superior**: O Maven é uma ferramenta de automação de build para projetos Java. Você pode baixá-lo do site oficial do Maven ou instalá-lo via gerenciador de pacotes.
    ```bash
    sudo apt install -y maven
    ```

### Configuração da Chave da API

O projeto utiliza a API HG Weather para obter dados de previsão do tempo. É necessário configurar a chave da API como uma variável de ambiente. Se você não tiver uma chave, o cliente usará uma chave padrão que pode ter acesso limitado.

Defina a variável de ambiente `HG_WEATHER_API_KEY` com sua chave da API:

```bash
export HG_WEATHER_API_KEY="SUA_CHAVE_DA_API_AQUI"
```

Substitua `SUA_CHAVE_DA_API_AQUI` pela sua chave real da API HG Weather.

### Compilação do Projeto

1.  **Navegue até o diretório raiz do projeto:**
    ```bash
    cd /home/ubuntu/gs01-2025-soa-java
    ```

2.  **Compile o projeto usando Maven:**
    ```bash
    mvn clean install
    ```
    Este comando irá limpar o projeto, baixar as dependências necessárias e compilar o código-fonte, gerando um arquivo JAR executável no diretório `target/`.

### Execução da Aplicação

Após a compilação bem-sucedida, você pode executar a aplicação Spring Boot usando o arquivo JAR gerado:

```bash
java -jar target/gs01-2025-soa-java-0.0.1-SNAPSHOT.jar
```

A aplicação será iniciada e estará acessível na porta `8080` por padrão. Você verá logs no console indicando que o Tomcat foi iniciado e a aplicação está pronta.

### Testando a API

Com a aplicação em execução, você pode acessar os endpoints da API usando um navegador web ou uma ferramenta como `curl` ou Postman.

*   **Endpoint Raiz:**
    ```
    http://localhost:8080/
    ```
    Este endpoint não retorna uma mensagem de boas-vindas, mas sim uma página de erro padrão do Spring Boot para rotas não mapeadas, pois o foco principal é o endpoint de clima.

*   **Endpoint de Previsão do Tempo:**
    ```
    http://localhost:8080/api/weather/{nomeDaCidade}
    ```
    Substitua `{nomeDaCidade}` pela cidade desejada (ex: `Sao%20Paulo,SP`).

    Exemplo usando `curl`:
    ```bash
    curl http://localhost:8080/api/weather/Sao%20Paulo,SP
    ```

    A resposta será um JSON contendo os dados de previsão do tempo formatados.

### Estrutura do Projeto Java

O projeto Java segue a estrutura padrão de um projeto Spring Boot com Maven:

```
gs01-2025-soa/
├── pom.xml
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/
│   │   │       └── example/
│   │   │           └── soa/
│   │   │               └── weather/
│   │   │                   ├── Gs012025SoaJavaApplication.java (Classe principal)
│   │   │                   ├── client/
│   │   │                   │   └── HgWeatherClient.java (Cliente da API externa)
│   │   │                   ├── config/
│   │   │                   │   └── AppConfig.java (Configuração de Beans)
│   │   │                   ├── controller/
│   │   │                   │   └── WeatherController.java (Controlador REST)
│   │   │                   ├── model/
│   │   │                   │   ├── ForecastDay.java (POJO para previsão diária)
│   │   │                   │   └── WeatherResponse.java (POJO para resposta de clima)
│   │   │                   └── service/
│   │   │                       └── WeatherService.java (Lógica de negócio)
│   │   └── resources/
│   │       └── application.properties (Configurações da aplicação)
│   └── test/
│       └── java/
└── target/ (Diretório de build)
```

Este projeto implementa uma API simples de previsão do tempo utilizando Java (Spring Boot), mantendo a arquitetura em camadas (cliente, serviço, controlador) e utilizando o Maven para gerenciamento de dependências e build.

