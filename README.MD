# Automation Matera

Projeto de automação de testes para a API Cat Facts e a tela de consulta de usuários de um sistema ERP. Este repositório contém testes escritos em Cucumber (Gherkin) para validar os seguintes cenários:

- **Testes Funcionais de Frontend:**
    - Validação dos filtros de pesquisa de usuários (por nome, email, status e tipo de pessoa – Pessoa Física / Pessoa Jurídica).
    - Validação do botão "Limpar Filtros" para resetar os inputs.
    - Cenários de caminhos felizes e infelizes.

- **Testes Funcionais de Backend:**
    - Validação do endpoint GET `/breeds` da API Cat Facts com cenários:
        - Retorno correto dos dados (status 200 e lista de raças de gatos).
        - Validação de parâmetros de paginação e filtros.
        - Validação de comportamento com método HTTP incorreto.
        - Validação do parâmetro `limit` para restringir o número de resultados retornados.
        - Cenário específico: ao passar um `limit` maior que o máximo permitido, a API retorna os metadados esperados.

  **Como visualizar os testes funcionais Frontend com a escrita em Gherkin:**
    - Acesse o arquivo `src/test/resources/Testes Funcionais de Frontend` e abra o arquivo `Cenarios de Teste`.

  **Como visualizar os testes funcionais Backend com a escrita em Gherkin:**
    - Acesse o arquivo `src/test/resources/Testes Funcionais de Backend` e abra o arquivo `Cenarios de Teste`.


- **Testes Automatizados:**
    - Implementação dos cenários acima utilizando Cucumber com Java e RestAssured.
    - Os testes estão organizados nas classes de Step Definitions e executados via um Runner (TestRunner.java).

      **Cenários Cobertos**

      - \*Caminho Feliz:\* Recuperação da lista de raças de gatos
      - \*Caminho Infeliz:\* Validação de método HTTP incorreto com mensagem de erro
      - \*Limite de Resultados:\* Verificação da quantidade exata de raças retornadas
      - \*Validação de Esquema:\* Confirmação do contrato da resposta via schema

## Pré-requisitos

- **Java 11** (ou versão compatível configurada no projeto)
- **Maven** instalado e configurado no PATH
- IDE de sua preferência (por exemplo, IntelliJ IDEA, Eclipse, VSCode) para executar a classe Runner

## Configurações Adicionais

### Maven Compiler Plugin
O projeto está configurado para compilar utilizando Java 11. Caso deseje atualizar para outra versão, ajuste as propriedades:
 `<maven.compiler.source>`, `<maven.compiler.target>` e a tag `<release>` no `pom.xml`.

### Relatórios de Teste
Após a execução via Maven, os relatórios de teste estarão disponíveis na pasta `target/ExtentReport.html`.

### Relatórios de Cobertura
Os relatórios de cobertura de testes estarão disponíveis na pasta `target/site/jacoco/index.html`.
Os relatórios são gerados pelo plugin Jacoco e podem ser visualizados em um navegador:

```bash
   mvn clean test jacoco:report
```

## Como Baixar o Projeto

1. **Clone o repositório:**
   ```bash
   git clone https://github.com/tobiascorrea/matera-test.git

2. **Acesse o diretório do projeto:**
   ```bash
   cd automation-matera

3. **Execute o comando para baixar as dependências do Maven:**
   ```bash
    mvn clean install

## Execução dos Testes

### Via IDE (Classe Runner)
1. Abra o projeto em sua IDE.
2. Navegue até a classe `TestRunner.java` (localizada em `src/test/java/runners/`).
3. Clique com o botão direito na classe e selecione **Run 'TestRunner'**.

### Via Maven
Para executar os testes através do Maven, utilize o comando abaixo:

O Maven utilizará o plugin Surefire para identificar e executar os testes definidos na classe Runner.

```bash
   mvn clean test