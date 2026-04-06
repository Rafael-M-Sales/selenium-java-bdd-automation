package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

/**
 * Page Object: DemoQAPages
 * Gerencia o site de treinamento DemoQA.
 */
public class DemoQAPages extends BasePage {
    private final By btnSubmit = By.id("submit");

    public DemoQAPages(WebDriver driver) {
        super(driver);
    }

    /**
     * Abre a seção de campos de texto.
     */
    public void visitarTextBox() {
        driver.get("https://demoqa.com/text-box");
    }

    /**
     * Preenche o formulário.
     */
    public void preencherForm(String n, String e, String d) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("userName"))).sendKeys(n);
        driver.findElement(By.id("userEmail")).sendKeys(e);
        driver.findElement(By.id("currentAddress")).sendKeys(d);
    }

    /**
     * Envia os dados.
     */
    public void enviar() {
        jsClick(btnSubmit);
    }

    /**
     * Captura o bloco de texto gerado após o envio.
     */
    public String obterOutput() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("output"))).getText();
    }
}
