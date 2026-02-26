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
 * Hooks de configuração do WebDriver para testes de UI
 * Projeto: selenium-java-bdd
 */
public class Hooks {

    private static WebDriver driver;

    @BeforeAll
    public static void configurarAmbiente() {
        System.out.println("=== Inicializando suite de testes ===");
    }

    @Before(order = 1)
    public void iniciarDriver(Scenario scenario) {
        System.out.println("=== Iniciando cenário: " + scenario.getName() + " ===");
        // Apenas inicializa o driver para cenários de UI (não-API)
        boolean isApiTest = scenario.getSourceTagNames().contains("@api");
        if (!isApiTest) {
            WebDriverManager.chromedriver().setup();
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--headless");
            options.addArguments("--no-sandbox");
            options.addArguments("--disable-dev-shm-usage");
            options.addArguments("--window-size=1280,720");
            driver = new ChromeDriver(options);
            driver.manage().window().maximize();
        }
    }

    @After(order = 1)
    public void finalizarDriver(Scenario scenario) {
        // Captura screenshot em caso de falha
        if (scenario.isFailed() && driver != null) {
            byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshot, "image/png", scenario.getName() + "_falha");
            System.out.println("📸 Screenshot capturada: " + scenario.getName());
        }

        if (driver != null) {
            driver.quit();
            driver = null;
            System.out.println("=== Driver encerrado ===");
        }
    }

    @AfterAll
    public static void finalizarSuite() {
        System.out.println("=== Suite de testes finalizada ===");
    }

    /**
     * Retorna o WebDriver ativo para uso nos Page Objects
     */
    public static WebDriver getDriver() {
        if (driver == null) {
            throw new IllegalStateException(
                    "WebDriver não inicializado. Verifique se o cenário não está tagueado como @api.");
        }
        return driver;
    }
}
