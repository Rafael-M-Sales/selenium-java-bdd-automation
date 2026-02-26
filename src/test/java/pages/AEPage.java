package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class AEPage {
    private final WebDriver driver;
    private final WebDriverWait wait;

    public AEPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void visitar() {
        driver.get("https://automationexercise.com");
    }

    public void navegarParaLogin() {
        wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Signup / Login"))).click();
    }

    public void login(String e, String p) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input[data-qa='login-email']")))
                .sendKeys(e);
        driver.findElement(By.cssSelector("input[data-qa='login-password']")).sendKeys(p);
        driver.findElement(By.cssSelector("button[data-qa='login-button']")).click();
    }

    public boolean verificarLogado() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(By.partialLinkText("Logged in as")))
                .isDisplayed();
    }
}
