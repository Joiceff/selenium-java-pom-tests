package br.com.joice.web.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;
import java.util.stream.Collectors;

public class CarrinhoPage extends BasePage {

    private static final By ITENS = dataTest("inventory-item-name");
    private static final By CHECKOUT = By.id("checkout");

    public CarrinhoPage(WebDriver driver) {
        super(driver);
    }

    public List<String> itens() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(CHECKOUT));
        return driver.findElements(ITENS).stream().map(e -> e.getText()).collect(Collectors.toList());
    }

    public CheckoutPage iniciarCheckout() {
        clicar(CHECKOUT);
        return new CheckoutPage(driver);
    }
}
