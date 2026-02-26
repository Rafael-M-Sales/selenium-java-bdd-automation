package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class SauceLoginPage {
    private final WebDriver driver;
    private final WebDriverWait wait;

    public SauceLoginPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void visitar() {
        driver.get("https://www.saucedemo.com");
    }

    public void login(String u, String p) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("user-name"))).sendKeys(u);
        driver.findElement(By.id("password")).sendKeys(p);
        driver.findElement(By.id("login-button")).click();
    }

    public String obterUrl() {
        return driver.getCurrentUrl();
    }

    public int contarProdutos() {
        return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.className("inventory_item"))).size();
    }
}
