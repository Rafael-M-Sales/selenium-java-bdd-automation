package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

/**
 * BasePage: Classe base para todos os Page Objects.
 * Contém abstrações de alto nível para tornar os testes mais resilientes e
 * fáceis de debugar.
 */
public abstract class BasePage {
    protected WebDriver driver;
    protected WebDriverWait wait;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    /**
     * Clica em um elemento garantindo que ele esteja clicável.
     */
    protected void click(By locator) {
        highlightElement(locator);
        wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
    }

    /**
     * Clica em um elemento usando JavaScript (útil quando o clique é interceptado).
     */
    protected void jsClick(By locator) {
        highlightElement(locator);
        WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(locator));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
    }

    /**
     * Rola a página até o elemento.
     */
    protected void scrollToElement(By locator) {
        WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(locator));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
    }

    /**
     * Digita em um campo após limpar o conteúdo anterior.
     */
    protected void sendKeys(By locator, String text) {
        highlightElement(locator);
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        element.clear();
        element.sendKeys(text);
    }

    /**
     * Espera e retorna o texto de um elemento.
     */
    protected String getText(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator)).getText();
    }

    /**
     * Verifica se um elemento está visível (sem lançar exceção imediata).
     */
    protected boolean isDisplayed(By locator) {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(locator)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Aplica um highlight visual no elemento antes da interação (ajuda muito em
     * demos e debug).
     */
    private void highlightElement(By locator) {
        try {
            WebElement element = driver.findElement(locator);
            String originalStyle = element.getAttribute("style");
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].setAttribute('style', 'border: 2px solid red; background: yellow;');",
                    element);
            Thread.sleep(100); // Breve pausa visual
            js.executeScript("arguments[0].setAttribute('style', '" + originalStyle + "');", element);
        } catch (Exception ignored) {
        }
    }
}
