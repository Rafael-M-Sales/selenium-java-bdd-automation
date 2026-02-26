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
 * 
 * Esta classe representa a página de login do Heroku "The Internet".
 * O uso de Page Objects facilita a manutenção pois centraliza seletores e
 * ações.
 */
public class LoginPage {

    // O WebDriver é a interface que controla o navegador.
    private final WebDriver driver;
    // O WebDriverWait permite aguardar que elementos apareçam antes de interagir
    // (Sync).
    private final WebDriverWait wait;

    // ===== LOCATORS (Seletores) =====
    // Usamos o objeto 'By' para definir como encontrar os elementos no HTML.
    private final By usernameInput = By.id("username");
    private final By passwordInput = By.id("password");
    private final By loginButton = By.cssSelector("button[type='submit']");
    private final By flashMessage = By.id("flash");
    private final By logoutButton = By.cssSelector("a.button.secondary");

    private static final String BASE_URL = "https://the-internet.herokuapp.com";

    /**
     * Construtor da página. Recebe o driver vindo dos Steps.
     */
    public LoginPage(WebDriver driver) {
        this.driver = driver;
        // Configuramos um wait padrão de 10 segundos.
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    /**
     * Navega até a página de login usando a URL base.
     */
    public void visitar() {
        driver.get(BASE_URL + "/login");
    }

    /**
     * Digita o usuário após garantir que o campo está visível.
     */
    public void preencherUsuario(String username) {
        WebElement campo = wait.until(ExpectedConditions.visibilityOfElementLocated(usernameInput));
        campo.clear(); // Limpa o campo antes de digitar (boa prática)
        campo.sendKeys(username);
    }

    /**
     * Digita a senha.
     */
    public void preencherSenha(String senha) {
        WebElement campo = wait.until(ExpectedConditions.visibilityOfElementLocated(passwordInput));
        campo.clear();
        campo.sendKeys(senha);
    }

    /**
     * Clica no botão de login após garantir que ele é clicável.
     */
    public void clicarLogin() {
        wait.until(ExpectedConditions.elementToBeClickable(loginButton)).click();
    }

    /**
     * Clica no botão de logout.
     */
    public void clicarLogout() {
        wait.until(ExpectedConditions.elementToBeClickable(logoutButton)).click();
    }

    /**
     * Captura o texto do alerta (banner verde ou vermelho no topo).
     */
    public String obterMensagem() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(flashMessage)).getText();
    }

    /**
     * Retorna a URL atual do navegador para validações de redirecionamento.
     */
    public String obterUrlAtual() {
        return driver.getCurrentUrl();
    }

    /**
     * Método de conveniência que realiza o fluxo completo de login.
     */
    public void realizarLogin(String username, String senha) {
        preencherUsuario(username);
        preencherSenha(senha);
        clicarLogin();
    }
}
