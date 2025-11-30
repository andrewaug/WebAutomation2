package com.amazon.driver;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;

import java.time.Duration;

public class EdgeDriverProvider implements DriverFactory {

    private static final ThreadLocal<WebDriver> THREAD_LOCAL = new ThreadLocal<>();

    @Override
    public WebDriver getDriver(){
        WebDriver driver = THREAD_LOCAL.get();
        if(driver != null){
            return driver;
        }

        WebDriverManager.edgedriver().setup();
        EdgeOptions options = new EdgeOptions();
        options.addArguments("--no-notifications");
        driver = new EdgeDriver(options);
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(20));
        driver.manage().timeouts().scriptTimeout(Duration.ofSeconds(10));

        THREAD_LOCAL.set(driver);
        return driver;
    }

}
