# ☕ selenium-java-bdd

Projeto de automação de testes com **Selenium 4 + Java 17 + Cucumber 7 + JUnit5 + Allure + Maven**.

## 📦 Stack

| Tecnologia | Versão | Função |
|---|---|---|
| Selenium Java | 4.27.0 | WebDriver para testes de UI |
| WebDriverManager | 5.9.2 | Gerenciamento automático de drivers |
| Cucumber Java | 7.14.0 | BDD/Gherkin |
| JUnit5 | 5.10.0 | Framework de testes e unit tests |
| Allure | 2.24.0 | Relatórios de testes |
| REST Assured | 5.4.0 | Testes de API REST |
| Maven | 3.x | Build e gerenciamento de dependências |

## 📁 Estrutura

```
selenium-java-bdd/
├── src/
│   ├── main/
│   │   └── java/
│   │       └── utils/
│   │           └── StringUtils.java   # Utilitários de string
│   └── test/
│       ├── java/
│       │   ├── hooks/
│       │   │   └── Hooks.java         # WebDriver lifecycle
│       │   ├── pages/
│       │   │   └── LoginPage.java     # Page Object
│       │   ├── runner/
│       │   │   └── TestRunner.java    # Ponto de entrada Cucumber
│       │   ├── steps/
│       │   │   ├── LoginSteps.java    # Steps de login
│       │   │   └── ApiSteps.java      # Steps de API
│       │   └── unit/
│       │       └── StringUtilsTest.java  # Unit tests JUnit5
│       └── resources/
│           ├── features/
│           │   ├── login.feature      # Gherkin pt-BR
│           │   └── api.feature
│           └── allure.properties
└── pom.xml
```

## 🚀 Como Executar

### Pré-requisitos
- Java 17+
- Maven 3.8+
- Google Chrome instalado

### Executar todos os testes
```bash
mvn test
```

### Executar por tag
```bash
mvn test -Dcucumber.filter.tags="@login"
mvn test -Dcucumber.filter.tags="@api"
mvn test -Dcucumber.filter.tags="@happy-path"
```

### Gerar e abrir relatório Allure
```bash
mvn allure:report
mvn allure:serve
```

## 🏷️ Tags disponíveis

| Tag | Descrição |
|---|---|
| `@login` | Cenários de login |
| `@api` | Testes de API REST (REST Assured) |
| `@happy-path` | Fluxos de sucesso |
| `@sad-path` | Fluxos de erro |
| `@logout` | Cenários de logout |
| `@crud` | Operações CRUD na API |
| `@ignore` | Cenários ignorados na execução |

## 🌐 Alvos dos Testes

- **UI:** [https://the-internet.herokuapp.com](https://the-internet.herokuapp.com)
- **API:** [https://reqres.in](https://reqres.in)
