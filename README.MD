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

- **Testes Automatizados:**
    - Implementação dos cenários acima utilizando Cucumber com Java e RestAssured.
    - Os testes estão organizados nas classes de Step Definitions e executados via um Runner (TestRunner.java).

## Pré-requisitos

- **Java 11** (ou versão compatível configurada no projeto)
- **Maven** instalado e configurado no PATH
- IDE de sua preferência (por exemplo, IntelliJ IDEA, Eclipse, VSCode) para executar a classe Runner


## Como Baixar o Projeto

1. **Clone o repositório:**
   ```bash
   git clone https://github.com/seu-usuario/automation-matera.git

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

