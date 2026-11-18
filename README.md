# 🌐 Selenium Java — Page Object Model

[![Selenium E2E Tests](https://github.com/Joiceff/selenium-java-pom-tests/actions/workflows/tests.yml/badge.svg)](https://github.com/Joiceff/selenium-java-pom-tests/actions/workflows/tests.yml)

Automação E2E da aplicação [SauceDemo](https://www.saucedemo.com) em **Java + Selenium WebDriver 4**, organizada com o padrão **Page Object Model** e executada em **Chrome e Firefox** no GitHub Actions.

## 🧰 Tecnologias

| Ferramenta | Uso |
|---|---|
| Java 11+ / Maven | Linguagem e build |
| Selenium WebDriver 4 | Automação do navegador (Selenium Manager resolve os drivers) |
| JUnit 5 | Testes, `@ParameterizedTest` e extensões |
| Page Object Model | Separação entre páginas e testes, API fluente |
| GitHub Actions | CI em matriz **Chrome × Firefox**, headless |

## 🏗️ Boas práticas aplicadas

- **Page Objects com interface fluente** (`login → produtos → carrinho → checkout`)
- **Esperas explícitas** centralizadas na `BasePage` (sem `Thread.sleep`)
- Locators estáveis via atributos `data-test`
- **Testes data-driven** com `@CsvSource` para cenários de login inválido
- **Screenshot automático em falhas** (`target/screenshots`) via extensão JUnit
- Navegador e modo headless configuráveis por propriedade (`-Dbrowser`, `-Dheadless`)

## 📁 Estrutura

```
src/test/java/br/com/joice/web/
├── pages/     # BasePage, LoginPage, ProdutosPage, CarrinhoPage, CheckoutPage
├── support/   # DriverFactory, BaseTest, ScreenshotOnFailure
└── tests/     # LoginTest, CompraTest
```

## ✅ Cenários (11)

- **Login:** usuário válido; bloqueado, senha errada, usuário vazio e senha vazia (parametrizado)
- **Carrinho:** adicionar/remover produtos e conferir itens
- **Checkout:** compra completa e validação de CEP obrigatório
- **Ordenação:** menor preço e nome de Z → A

## ▶️ Como executar

```bash
mvn test                                  # Chrome headless
mvn test -Dheadless=false                 # ver o navegador
mvn test -Dbrowser=firefox                # Firefox
mvn test -Dgroups=smoke                   # apenas testes @Tag("smoke")
```

---
👩‍💻 Desenvolvido por **Joice Ferreira** — QA Sênior | [LinkedIn](https://www.linkedin.com/in/joice-ferreira-a04227126)
