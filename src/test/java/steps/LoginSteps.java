package steps;

import hooks.Hooks;
import io.cucumber.java.pt.*;
import org.junit.jupiter.api.Assertions;
import pages.LoginPage;

/**
 * Step Definitions: Login
 * Projeto: selenium-java-bdd
 * 
 * Este arquivo conecta os passos do Gherkin escrito em Português
 * com os métodos Java do Page Object.
 */
public class LoginSteps {

    private final LoginPage loginPage;

    /**
     * O construtor pega o WebDriver que foi inicializado no Hooks.
     */
    public LoginSteps() {
        this.loginPage = new LoginPage(Hooks.getDriver());
    }

    @Dado("que estou na página de login")
    public void queEstouNaPaginaDeLogin() {
        loginPage.visitar();
    }

    @Quando("eu preencho o campo de usuário com {string}")
    public void euPreenchoOCampoDeUsuarioComString(String username) {
        loginPage.preencherUsuario(username);
    }

    @Quando("eu preencho o campo de senha com {string}")
    public void euPreenchoOCampoDeSenhaComString(String senha) {
        loginPage.preencherSenha(senha);
    }

    @Quando("eu clico no botão de login")
    public void euClicoNoBotaoDeLogin() {
        loginPage.clicarLogin();
    }

    @Quando("eu clico no botão de logout")
    public void euClicoNoBotaoDeLogout() {
        loginPage.clicarLogout();
    }

    @Então("eu devo ser redirecionado para a área segura")
    public void euDevoSerRedirecionadoParaAAreaSegura() {
        // Assertions.assertTrue valida se uma condição é verdadeira (validação do
        // teste).
        Assertions.assertTrue(
                loginPage.obterUrlAtual().contains("/secure"),
                "URL esperada deve conter '/secure'");
    }

    @Então("devo ver a mensagem {string}")
    public void devoVerAMensagemString(String mensagem) {
        Assertions.assertTrue(
                loginPage.obterMensagem().contains(mensagem),
                "Mensagem esperada: '" + mensagem + "' | Mensagem real: '" + loginPage.obterMensagem() + "'");
    }

    @Então("devo ver a mensagem de erro {string}")
    public void devoVerAMensagemDeErroString(String mensagem) {
        Assertions.assertTrue(
                loginPage.obterMensagem().contains(mensagem),
                "Mensagem de erro esperada: '" + mensagem + "'");
    }

    @Então("eu devo ver a mensagem de logout {string}")
    public void euDevoVerAMensagemDeLogoutString(String mensagem) {
        Assertions.assertTrue(
                loginPage.obterMensagem().contains(mensagem),
                "Mensagem de logout esperada: '" + mensagem + "'");
    }
}
