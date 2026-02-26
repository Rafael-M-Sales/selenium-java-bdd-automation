package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

/**
 * Page Object: LoginPage
 * Projeto: selenium-java-bdd
 * Padrão: Page Object Model (POM)
 */
public class LoginPage {

    private final WebDriver driver;
    private final WebDriverWait wait;

    // ===== LOCATORS =====
    private final By usernameInput = By.id("username");
    private final By passwordInput = By.id("password");
    private final By loginButton   = By.cssSelector("button[type='submit']");
    private final By flashMessage  = By.id("flash");
    private final By logoutButton  = By.cssSelector("a.button.secondary");

    private static final String BASE_URL = "https://the-internet.herokuapp.com";

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.wait   = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    /**
     * Navega até a página de login
     */
    public void visitar() {
        driver.get(BASE_URL + "/login");
    }

    /**
     * Preenche o campo de usuário
     */
    public void preencherUsuario(String username) {
        WebElement campo = wait.until(ExpectedConditions.visibilityOfElementLocated(usernameInput));
        campo.clear();
        campo.sendKeys(username);
    }

    /**
     * Preenche o campo de senha
     */
    public void preencherSenha(String senha) {
        WebElement campo = wait.until(ExpectedConditions.visibilityOfElementLocated(passwordInput));
        campo.clear();
        campo.sendKeys(senha);
    }

    /**
     * Clica no botão de login
     */
    public void clicarLogin() {
        wait.until(ExpectedConditions.elementToBeClickable(loginButton)).click();
    }

    /**
     * Clica no botão de logout
     */
    public void clicarLogout() {
        wait.until(ExpectedConditions.elementToBeClickable(logoutButton)).click();
    }

    /**
     * Retorna o texto da mensagem de flash (sucesso ou erro)
     */
    public String obterMensagem() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(flashMessage)).getText();
    }

    /**
     * Retorna a URL atual
     */
    public String obterUrlAtual() {
        return driver.getCurrentUrl();
    }

    /**
     * Realiza o login completo (usuário + senha + botão)
     */
    public void realizarLogin(String username, String senha) {
        preencherUsuario(username);
        preencherSenha(senha);
        clicarLogin();
    }
}
