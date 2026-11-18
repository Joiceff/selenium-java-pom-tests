package br.com.joice.web.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import java.util.List;
import java.util.stream.Collectors;

public class ProdutosPage extends BasePage {

    private static final By TITULO = dataTest("title");
    private static final By BADGE_CARRINHO = dataTest("shopping-cart-badge");
    private static final By LINK_CARRINHO = dataTest("shopping-cart-link");
    private static final By ORDENACAO = dataTest("product-sort-container");
    private static final By PRECOS = dataTest("inventory-item-price");
    private static final By NOMES = dataTest("inventory-item-name");

    public ProdutosPage(WebDriver driver) {
        super(driver);
    }

    public String titulo() {
        return texto(TITULO);
    }

    public ProdutosPage adicionarAoCarrinho(String idProduto) {
        clicar(By.id("add-to-cart-" + idProduto));
        return this;
    }

    public ProdutosPage removerDoCarrinho(String idProduto) {
        clicar(By.id("remove-" + idProduto));
        return this;
    }

    public int quantidadeNoCarrinho() {
        List<?> badge = driver.findElements(BADGE_CARRINHO);
        return badge.isEmpty() ? 0 : Integer.parseInt(texto(BADGE_CARRINHO));
    }

    public ProdutosPage ordenarPor(String valor) {
        new Select(visivel(ORDENACAO)).selectByValue(valor);
        return this;
    }

    public List<Double> precos() {
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(PRECOS));
        return driver.findElements(PRECOS).stream()
                .map(e -> Double.parseDouble(e.getText().replace("$", "")))
                .collect(Collectors.toList());
    }

    public List<String> nomes() {
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(NOMES));
        return driver.findElements(NOMES).stream().map(e -> e.getText()).collect(Collectors.toList());
    }

    public CarrinhoPage abrirCarrinho() {
        clicar(LINK_CARRINHO);
        return new CarrinhoPage(driver);
    }
}
