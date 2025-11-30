package com.amazon.base;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.Duration;

public class BasePage extends Page{

    //constructor

    protected WebDriver driver;

    public BasePage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(this.driver, this);
    }

    protected Boolean safeClick(WebElement element) {
        int attempts = 0;
        // ensure visible/clickable and scroll into view first
        try {
            wait.until(ExpectedConditions.visibilityOf(element));
        } catch (Exception ignored) {}

        scrollToElement(element, 100);

        while (attempts < 3) {
            try {
                wait.until(ExpectedConditions.elementToBeClickable(element));
                element.click();
                return true;
            } catch (ElementClickInterceptedException e) {
                // try Actions click as fallback
                try {
                    new Actions(driver).moveToElement(element).pause(Duration.ofMillis(100)).click().perform();
                    return true;
                } catch (Exception ignored) {}
                attempts++;
                sleep(200);
            } catch (StaleElementReferenceException e) {
                attempts++;
                sleep(200);
            } catch (Exception e) {
                // unknown issue, break to try JS click as last resort
                break;
            }
        }

        // last resort: JS click
        try {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
            return true;
        } catch (Exception ignored) {}
        return false;

    }

    public void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException ignored) {
            Thread.currentThread().interrupt();
        }
    }
    public void quitDriver(){
        driver.quit();
    }

    public String getTitle(){
        return driver.getTitle();
    }

    public String getCurrentURL(){
        return driver.getCurrentUrl();
    }

    @Override
    public String getPageTitle() {
        return driver.getTitle();
    }

    @Override
    public String getPageURL() {
        return driver.getCurrentUrl();
    }

    public void navigateToUrl(String url){
        driver.navigate().to( url );
    }

    public void scrollToElement(WebElement element, int offsetPx) {
        if (element == null) return;
        String script =
                "var rect = arguments[0].getBoundingClientRect();" +
                        "var absoluteTop = rect.top + window.pageYOffset;" +
                        "var target = absoluteTop - (window.innerHeight / 2) + arguments[1];" +
                        "window.scrollTo({ top: Math.max(0, target), behavior: 'auto' });";
        ((JavascriptExecutor) driver).executeScript(script, element, offsetPx);
    }


    public boolean isElementDisplayed(WebElement element) {
        try {
            return element.isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }
    //add methods that are common across pages
    //verify page title
    //verify current url
}
