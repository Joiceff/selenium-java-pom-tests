package br.com.joice.web.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage {

    private static final By USUARIO = By.id("user-name");
    private static final By SENHA = By.id("password");
    private static final By ENTRAR = By.id("login-button");
    private static final By ERRO = dataTest("error");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public ProdutosPage entrarCom(String usuario, String senha) {
        tentarEntrar(usuario, senha);
        return new ProdutosPage(driver);
    }

    public LoginPage tentarEntrar(String usuario, String senha) {
        preencher(USUARIO, usuario);
        preencher(SENHA, senha);
        clicar(ENTRAR);
        return this;
    }

    public String mensagemDeErro() {
        return texto(ERRO);
    }
}
