package br.com.joice.web.support;

import br.com.joice.web.pages.LoginPage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.WebDriver;

@ExtendWith(ScreenshotOnFailure.class)
public abstract class BaseTest {

    protected static final String BASE_URL = System.getProperty("base.url", "https://www.saucedemo.com");
    protected static final String SENHA = "secret_sauce";

    protected WebDriver driver;
    protected LoginPage loginPage;

    @BeforeEach
    void abrirNavegador() {
        driver = DriverFactory.create();
        driver.get(BASE_URL);
        loginPage = new LoginPage(driver);
    }

    @AfterEach
    void fecharNavegador() {
        if (driver != null) {
            driver.quit();
        }
    }

    public WebDriver getDriver() {
        return driver;
    }
}
