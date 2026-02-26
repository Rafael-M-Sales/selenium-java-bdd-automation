package steps;

import hooks.Hooks;
import io.cucumber.java.pt.*;
import org.junit.jupiter.api.Assertions;
import pages.AEPage;

public class AESteps {
    private final AEPage ae = new AEPage(Hooks.getDriver());

    @Dado("que estou na página inicial do Automation Exercise \\(Selenium)")
    public void queEstouNaPaginaInicialDoAutomationExerciseSelenium() {
        ae.visitar();
    }

    @Quando("eu navego para Login")
    public void euNavegoParaLogin() {
        ae.navegarParaLogin();
    }

    @Quando("eu entro com e-mail {string} e senha {string}")
    public void euEntroComEMailESenha(String e, String s) {
        ae.login(e, s);
    }

    @Então("verifico o login com sucesso")
    public void verificoOLoginComSucesso() {
        Assertions.assertTrue(ae.verificarLogado(), "Não logou com sucesso no AE");
    }
}
