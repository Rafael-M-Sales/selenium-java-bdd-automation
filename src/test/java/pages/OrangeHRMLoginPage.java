package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

/**
 * Page Object: OrangeHRMLoginPage
 * Projeto: selenium-java-bdd
 * Alvo: https://opensource-demo.orangehrmlive.com
 */
public class OrangeHRMLoginPage {

    private final WebDriver driver;
    private final WebDriverWait wait;

    // ===== LOCATORS =====
    private final By usernameInput = By.name("username");
    private final By passwordInput = By.name("password");
    private final By loginButton = By.cssSelector("button[type='submit']");
    private final By errorMessage = By.cssSelector(".oxd-alert-content-text");
    private final By requiredMsg = By.cssSelector(".oxd-input-field-error-message");
    private final By userDropdown = By.cssSelector(".oxd-userdropdown-tab");
    private final By logoutOption = By.cssSelector(".oxd-userdropdown-link:last-child");

    private static final String BASE_URL = "https://opensource-demo.orangehrmlive.com";

    public OrangeHRMLoginPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    public void visitar() {
        driver.get(BASE_URL + "/web/index.php/auth/login");
    }

    public void preencherUsuario(String usuario) {
        WebElement campo = wait.until(
                ExpectedConditions.visibilityOfElementLocated(usernameInput));
        campo.clear();
        campo.sendKeys(usuario);
    }

    public void preencherSenha(String senha) {
        WebElement campo = wait.until(
                ExpectedConditions.visibilityOfElementLocated(passwordInput));
        campo.clear();
        campo.sendKeys(senha);
    }

    public void clicarLogin() {
        wait.until(ExpectedConditions.elementToBeClickable(loginButton)).click();
    }

    public void fazerLogin(String usuario, String senha) {
        preencherUsuario(usuario);
        preencherSenha(senha);
        clicarLogin();
    }

    public String obterMensagemErro() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(errorMessage)).getText();
    }

    public boolean mensagensObrigatoriasVisiveis() {
        return !driver.findElements(requiredMsg).isEmpty();
    }

    public void fazerLogout() {
        wait.until(ExpectedConditions.elementToBeClickable(userDropdown)).click();
        wait.until(ExpectedConditions.elementToBeClickable(logoutOption)).click();
    }

    public String obterUrlAtual() {
        return driver.getCurrentUrl();
    }
}
