package br.com.joice.web.support;

import org.junit.jupiter.api.extension.AfterTestExecutionCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

/**
 * Captura screenshot em target/screenshots quando um teste falha.
 * Roda antes do @AfterEach fechar o navegador.
 */
public class ScreenshotOnFailure implements AfterTestExecutionCallback {

    @Override
    public void afterTestExecution(ExtensionContext context) {
        if (context.getExecutionException().isEmpty()) {
            return;
        }
        Object instancia = context.getRequiredTestInstance();
        if (!(instancia instanceof BaseTest)) {
            return;
        }
        WebDriver driver = ((BaseTest) instancia).getDriver();
        if (driver == null) {
            return;
        }
        try {
            Path destino = Paths.get("target", "screenshots", context.getDisplayName().replaceAll("[^a-zA-Z0-9-_]", "_") + ".png");
            Files.createDirectories(destino.getParent());
            Path arquivo = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE).toPath();
            Files.copy(arquivo, destino, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            System.err.println("Não foi possível salvar o screenshot: " + e.getMessage());
        }
    }
}
