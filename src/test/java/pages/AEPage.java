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
public class AEPage extends BasePage {

    private final By signupLoginLink = By.linkText("Signup / Login");
    private final By emailInput = By.cssSelector("input[data-qa='login-email']");
    private final By passwordInput = By.cssSelector("input[data-qa='login-password']");
    private final By loginButton = By.cssSelector("button[data-qa='login-button']");
    private final By loggedInText = By.partialLinkText("Logged in as");

    public AEPage(WebDriver driver) {
        super(driver);
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
        click(signupLoginLink);
    }

    /**
     * Executa o login usando métodos abstraídos da BasePage.
     */
    public void login(String email, String password) {
        sendKeys(emailInput, email);
        sendKeys(passwordInput, password);
        jsClick(loginButton);
    }

    /**
     * Verifica se o elemento que indica "Logado como" está presente na tela.
     */
    public boolean verificarLogado() {
        return isDisplayed(loggedInText);
    }
}
