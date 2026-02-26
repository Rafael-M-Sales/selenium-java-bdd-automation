package steps;

import hooks.Hooks;
import io.cucumber.java.pt.*;
import org.junit.jupiter.api.Assertions;
import pages.SauceLoginPage;

public class SauceSteps {
    private final SauceLoginPage sauce = new SauceLoginPage(Hooks.getDriver());

    @Dado("que estou na página de login do SauceDemo \\(Selenium)")
    public void queEstouNaPaginaDeLoginDoSauceDemoSelenium() {
        sauce.visitar();
    }

    @Quando("eu logo com {string} e {string}")
    public void euLogoComESenha(String u, String p) {
        sauce.login(u, p);
    }

    @Então("sou levado à página de inventário")
    public void souLevadoAPaginaDeInventario() {
        Assertions.assertTrue(sauce.obterUrl().contains("inventory.html"));
    }

    @Então("vejo {string} produtos na lista")
    public void vejoProdutosNaLista(String q) {
        Assertions.assertEquals(Integer.parseInt(q), sauce.contarProdutos());
    }
}
