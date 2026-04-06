package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

/**
 * Page Object: DashboardPage (OrangeHRM)
 * Gerencia a tela principal após o login.
 */
public class DashboardPage {

    private final WebDriver driver;
    private final WebDriverWait wait;

    // ===== LOCATORS =====
    private final By dashboardTitle = By.cssSelector(".oxd-topbar-header-breadcrumb h6");
    private final By sidebarMenu = By.cssSelector(".oxd-main-menu");
    private final By sidebarItems = By.cssSelector(".oxd-main-menu-item");
    private final By searchInput = By.cssSelector(".oxd-input");
    private final By searchButton = By.cssSelector("button[type='submit']");
    private final By tableRows = By.cssSelector(".oxd-table-row--clickable");

    public DashboardPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    /**
     * Verifica se o menu lateral está visível.
     */
    public boolean dashboardVisivel() {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(sidebarMenu));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Clica em um item do menu lateral buscando pelo nome (ex: Admin, PIM, Leave).
     */
    public void clicarMenuLateral(String nomeMenu) {
        List<WebElement> itens = wait.until(
                ExpectedConditions.visibilityOfAllElementsLocatedBy(sidebarItems));
        for (WebElement item : itens) {
            // .trim() remove espaços e .equalsIgnoreCase() ignora maiúsculas/minúsculas.
            if (item.getText().trim().equalsIgnoreCase(nomeMenu)) {
                item.click();
                return;
            }
        }
        throw new RuntimeException("Item de menu não encontrado: " + nomeMenu);
    }

    /**
     * Obtém o texto do cabeçalho da seção atual.
     */
    public String obterTituloPagina() {
        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(dashboardTitle)).getText();
    }

    /**
     * Preenche o campo de busca global do dashboard.
     */
    public void pesquisar(String termo) {
        WebElement campo = wait.until(
                ExpectedConditions.visibilityOfElementLocated(searchInput));
        campo.clear();
        campo.sendKeys(termo);
    }

    /**
     * Clica no botão de pesquisa.
     */
    public void clicarPesquisar() {
        wait.until(ExpectedConditions.elementToBeClickable(searchButton)).click();
    }

    /**
     * Verifica se um texto específico está presente em qualquer linha das tabelas
     * exibidas.
     */
    public boolean textoNosResultados(String texto) {
        try {
            // Aguarda a tabela carregar.
            wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(tableRows));
            List<WebElement> linhas = driver.findElements(tableRows);
            // Uso de Stream API do Java 8+ para busca funcional.
            return linhas.stream().anyMatch(l -> l.getText().contains(texto));
        } catch (Exception e) {
            return false;
        }
    }
}
