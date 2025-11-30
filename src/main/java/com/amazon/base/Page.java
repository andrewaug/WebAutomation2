package com.amazon.base;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.Objects;

public abstract class Page {

    WebDriver driver;
    WebDriverWait wait;

    public Page(WebDriver driver) {
        this.driver = Objects.requireNonNull(driver, "WebDriver instance cannot be null");
        long waitSeconds = Long.parseLong(System.getProperty("webdriver.wait.seconds","15"));
        this.wait = new WebDriverWait(this.driver, Duration.ofSeconds(waitSeconds));

    }

    public abstract String getPageTitle();

    public abstract String getPageURL();

    //protected because only child classes reuse it and not part of public contract.
    protected final WebElement getVisibleElement(By locator){
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    protected final WebElement getVisibleElement(WebElement element ){
        return wait.until(ExpectedConditions.visibilityOf(element) );
    }



    protected final String getText(By locator) {
        return getVisibleElement(locator).getText();

    }

    protected final void waitForElementVisibility(By locator){
        wait.until(ExpectedConditions.visibilityOfElementLocated( locator ));
    }
    protected final void waitForPageTitle(String title) {
        wait.until(ExpectedConditions.titleContains(title));
    }
    public <Tpage extends BasePage> Tpage getInstance(Class<Tpage> pageClassName) {
        try {
            return pageClassName.getDeclaredConstructor( WebDriver.class).newInstance(this.driver);
        } catch (ReflectiveOperationException e) {
            throw new IllegalStateException( "Failed to create page instance: " + pageClassName.getSimpleName(), e);
        }
    }
}
