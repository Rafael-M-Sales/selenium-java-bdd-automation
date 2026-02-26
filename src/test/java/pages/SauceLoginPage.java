package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

/**
 * Page Object: SauceLoginPage
 * Gerencia a página de login do SauceDemo.
 */
public class SauceLoginPage {
    private final WebDriver driver;
    private final WebDriverWait wait;

    public SauceLoginPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    /**
     * Abre o site da SauceLabs diretamente.
     */
    public void visitar() {
        driver.get("https://www.saucedemo.com");
    }

    /**
     * Realiza o login completo preenchendo os campos.
     */
    public void login(String u, String p) {
        // Aguarda o campo de usuário aparecer antes de começar.
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("user-name"))).sendKeys(u);
        driver.findElement(By.id("password")).sendKeys(p);
        driver.findElement(By.id("login-button")).click();
    }

    /**
     * Obtém a URL para validar redirecionamentos.
     */
    public String obterUrl() {
        return driver.getCurrentUrl();
    }

    /**
     * Conta quantos itens de inventário estão sendo exibidos.
     */
    public int contarProdutos() {
        return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.className("inventory_item"))).size();
    }
}
