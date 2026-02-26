package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

/**
 * Page Object: AEPage
 * Gerencia o site Automation Exercise no Selenium.
 */
public class AEPage {
    private final WebDriver driver;
    private final WebDriverWait wait;

    public AEPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    /**
     * Navega para a home page.
     */
    public void visitar() {
        driver.get("https://automationexercise.com");
    }

    /**
     * Clica no link de login no cabeçalho.
     */
    public void navegarParaLogin() {
        wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Signup / Login"))).click();
    }

    /**
     * Executa o login usando seletores de atributo customizados.
     */
    public void login(String e, String p) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input[data-qa='login-email']")))
                .sendKeys(e);
        driver.findElement(By.cssSelector("input[data-qa='login-password']")).sendKeys(p);
        driver.findElement(By.cssSelector("button[data-qa='login-button']")).click();
    }

    /**
     * Verifica se o elemento que indica "Logado como" está presente na tela.
     */
    public boolean verificarLogado() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(By.partialLinkText("Logged in as")))
                .isDisplayed();
    }
}
