package steps;

import hooks.Hooks;
import io.cucumber.java.pt.*;
import org.junit.jupiter.api.Assertions;
import pages.DemoQAPages;

public class DemoQASteps {
    private final DemoQAPages demoqa = new DemoQAPages(Hooks.getDriver());

    @Dado("que estou na página Text Box do DemoQA")
    public void queEstouNaPaginaTextBoxDoDemoQA() {
        demoqa.visitarTextBox();
    }

    @Quando("eu preencho o nome {string}, email {string} e endereço {string}")
    public void euPreenchoONomeEmailEEndereco(String n, String e, String d) {
        demoqa.preencherForm(n, e, d);
    }

    @Quando("clico em enviar")
    public void clicoEmEnviar() {
        demoqa.enviar();
    }

    @Então("devo ver os dados confirmados abaixo do formulário")
    public void devoVerOsDadosConfirmadosAbaixoDoFormulario() {
        Assertions.assertTrue(demoqa.obterOutput().contains("João Silva"));
    }
}
