package br.com.joice.web.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckoutPage extends BasePage {

    private static final By NOME = By.id("first-name");
    private static final By SOBRENOME = By.id("last-name");
    private static final By CEP = By.id("postal-code");
    private static final By CONTINUAR = By.id("continue");
    private static final By FINALIZAR = By.id("finish");
    private static final By TOTAL = dataTest("total-label");
    private static final By SUCESSO = dataTest("complete-header");
    private static final By ERRO = dataTest("error");

    public CheckoutPage(WebDriver driver) {
        super(driver);
    }

    public CheckoutPage preencherDados(String nome, String sobrenome, String cep) {
        preencher(NOME, nome);
        preencher(SOBRENOME, sobrenome);
        preencher(CEP, cep);
        clicar(CONTINUAR);
        return this;
    }

    public String total() {
        return texto(TOTAL);
    }

    public CheckoutPage finalizar() {
        clicar(FINALIZAR);
        return this;
    }

    public String mensagemDeSucesso() {
        return texto(SUCESSO);
    }

    public String mensagemDeErro() {
        return texto(ERRO);
    }
}
