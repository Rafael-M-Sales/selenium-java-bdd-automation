package steps;

import hooks.Hooks;
import io.cucumber.java.pt.*;
import org.junit.jupiter.api.Assertions;
import pages.DashboardPage;
import pages.OrangeHRMLoginPage;

/**
 * Step Definitions: OrangeHRM
 * Projeto: selenium-java-bdd
 */
public class OrangeHRMSteps {

    private final OrangeHRMLoginPage loginPage;
    private final DashboardPage dashboardPage;

    public OrangeHRMSteps() {
        // Inicializa as páginas usando o driver compartilhado.
        this.loginPage = new OrangeHRMLoginPage(Hooks.getDriver());
        this.dashboardPage = new DashboardPage(Hooks.getDriver());
    }

    // ===== LOGIN =====

    @Dado("que estou na página de login do OrangeHRM")
    public void queEstouNaPaginaDeLoginDoOrangeHRM() {
        loginPage.visitar();
    }

    /**
     * Step de fundo que agiliza o início do cenário já logado.
     */
    @Dado("que estou logado no OrangeHRM como {string}")
    public void queEstouLogadoNoOrangeHRMComo(String usuario) {
        loginPage.visitar();
        loginPage.fazerLogin(usuario, "admin123");
        Assertions.assertTrue(dashboardPage.dashboardVisivel(),
                "Dashboard não ficou visível após login");
    }

    @Quando("eu faço login com usuário {string} e senha {string}")
    public void euFacoLoginComUsuarioESenha(String usuario, String senha) {
        loginPage.fazerLogin(usuario, senha);
    }

    @Quando("eu clico em {string} sem preencher os campos")
    public void euClicoEmSemPreencherOsCampos(String botao) {
        loginPage.clicarLogin();
    }

    @Então("devo ser redirecionado para o Dashboard")
    public void devoSerRedirecionadoParaODashboard() {
        Assertions.assertTrue(dashboardPage.dashboardVisivel(),
                "Dashboard não está visível");
    }

    @Então("devo ver a mensagem {string}")
    public void devoVerAMensagem(String mensagem) {
        Assertions.assertTrue(
                loginPage.obterMensagemErro().contains(mensagem),
                "Mensagem esperada: '" + mensagem + "' | Real: '" + loginPage.obterMensagemErro() + "'");
    }

    // ===== NAVEGAÇÃO =====

    @Quando("eu clico em {string} no menu lateral")
    public void euClicoEmNoMenuLateral(String menu) {
        dashboardPage.clicarMenuLateral(menu);
    }

    @Então("o título da página deve ser {string}")
    public void oTituloDaPaginaDeveSer(String titulo) {
        String tituloPagina = dashboardPage.obterTituloPagina();
        Assertions.assertTrue(
                tituloPagina.contains(titulo),
                "Título esperado: '" + titulo + "' | Real: '" + tituloPagina + "'");
    }

    // ===== BUSCA =====

    @Quando("eu pesquiso pelo usuário {string} na busca")
    public void euPesquisoPeloUsuarioNaBusca(String usuario) {
        dashboardPage.pesquisar(usuario);
    }

    @Quando("eu clico em {string}")
    public void euClicoEm(String botao) {
        // Assume-se que o botão é o de pesquisar neste contexto.
        dashboardPage.clicarPesquisar();
    }

    @Então("devo ver {string} na lista de resultados")
    public void devoVerNaListaDeResultados(String texto) {
        Assertions.assertTrue(
                dashboardPage.textoNosResultados(texto),
                "Texto '" + texto + "' não encontrado nos resultados");
    }
}
