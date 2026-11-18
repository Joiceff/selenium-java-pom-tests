package br.com.joice.web.support;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

/**
 * Cria o WebDriver conforme as propriedades de execução:
 * -Dbrowser=chrome|firefox (padrão: chrome) e -Dheadless=true|false (padrão: true).
 * O driver é resolvido automaticamente pelo Selenium Manager.
 */
public final class DriverFactory {

    private DriverFactory() {
    }

    public static WebDriver create() {
        String browser = System.getProperty("browser", "chrome").toLowerCase();
        boolean headless = Boolean.parseBoolean(System.getProperty("headless", "true"));

        if (browser.equals("firefox")) {
            FirefoxOptions options = new FirefoxOptions();
            if (headless) options.addArguments("-headless");
            return new FirefoxDriver(options);
        }

        ChromeOptions options = new ChromeOptions();
        if (headless) options.addArguments("--headless=new");
        options.addArguments("--window-size=1366,768", "--no-sandbox", "--disable-dev-shm-usage");
        return new ChromeDriver(options);
    }
}
