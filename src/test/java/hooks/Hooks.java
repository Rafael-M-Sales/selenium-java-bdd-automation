package hooks;

import io.cucumber.java.After;
import io.cucumber.java.AfterAll;
import io.cucumber.java.Before;
import io.cucumber.java.BeforeAll;
import io.cucumber.java.Scenario;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

/**
 * Hooks: Ganchos de execução do Cucumber.
 * Projeto: selenium-java-bdd
 * 
 * Este arquivo controla a abertura e fechamento do navegador antes e após cada
 * cenário.
 */
public class Hooks {

    // Driver estático para ser compartilhado entre todas as classes de Steps.
    private static WebDriver driver;

    @BeforeAll
    public static void configurarAmbiente() {
        System.out.println("=== Inicializando suite de testes Selenium Java ===");
    }

    /**
     * Este código roda ANTES de cada cenário.
     * Order = 1 define a prioridade de execução.
     */
    @Before(order = 1)
    public void iniciarDriver(Scenario scenario) {
        System.out.println("=== Iniciando cenário: " + scenario.getName() + " ===");

        // Verificamos se o cenário NÃO é um teste de API (que não precisa de
        // navegador).
        boolean isApiTest = scenario.getSourceTagNames().contains("@api");

        if (!isApiTest) {
            // WebDriverManager baixa o driver correto (chromedriver) automaticamente.
            WebDriverManager.chromedriver().setup();

            // Configurações do navegador (ChromeOptions).
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--headless"); // Roda sem abrir janela gráfica (ideal para CI/CD)
            options.addArguments("--no-sandbox");
            options.addArguments("--disable-dev-shm-usage");
            options.addArguments("--window-size=1280,720");

            driver = new ChromeDriver(options);
            driver.manage().window().maximize();
        }
    }

    /**
     * Este código roda APÓS cada cenário.
     */
    @After(order = 1)
    public void finalizarDriver(Scenario scenario) {
        // Se o teste falhou, tiramos um print da tela para ajudar no debug.
        if (scenario.isFailed() && driver != null) {
            byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
            // Anexa o print no relatório do Cucumber/Allure.
            scenario.attach(screenshot, "image/png", "Evidência_de_Falha");
            System.out.println("📸 Screenshot capturada devido a falha.");
        }

        // Encerra o navegador para liberar memória.
        if (driver != null) {
            driver.quit();
            driver = null;
            System.out.println("=== Navegador encerrado ===");
        }
    }

    @AfterAll
    public static void finalizarSuite() {
        System.out.println("=== Suite de testes finalizada ===");
    }

    /**
     * Método estático acessível por qualquer classe para obter o driver ativo.
     */
    public static WebDriver getDriver() {
        if (driver == null) {
            throw new IllegalStateException(
                    "WebDriver não inicializado. Verifique se o cenário tem a tag @api por engano.");
        }
        return driver;
    }
}
