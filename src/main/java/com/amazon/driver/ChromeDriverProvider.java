package com.amazon.driver;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.File;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

public class ChromeDriverProvider implements  DriverFactory{
    private static final ThreadLocal<WebDriver> THREAD_DRIVER = new ThreadLocal<>();
    WebDriver driver;
    public  ChromeDriverProvider(){

        this.driver = getDriver();

    }
    @Override
    public WebDriver getDriver(){
        WebDriver driver = THREAD_DRIVER.get();
        if (driver != null) {
            return driver;
        }

/**
        You’re close. Issues and recommendations:
        - Don’t call `new ChromeDriver()` directly — manage the driver binary (use WebDriverManager) and pass `ChromeOptions`.
        - Make the provider stateless or thread\-safe (use `ThreadLocal` if tests run in parallel).
        - Allow configuration (headless, timeouts) via system properties or a config file.
                - Ensure proper lifecycle: quit the driver after use.
                - Keep implicit waits to a minimum; prefer explicit waits.

                Improved `ChromeDriverProvider` that uses WebDriverManager, configurable headless mode, basic hardening flags and a ThreadLocal to support parallel runs.

                Brief explanation: sets up the chromedriver binary, configures `ChromeOptions` from system properties, creates a thread\-local driver and returns it.
**/

// ensure chromedriver binary is available
        WebDriverManager.chromedriver().setup();

        ChromeOptions options = new ChromeOptions();
        String headless = System.getProperty("chrome.headless", "false");
        if (Boolean.parseBoolean(headless)) {
             // or "--headless" for older versions
        }

        // common flags for stability in CI
        //options.addArguments("--no-sandbox", "--disable-dev-shm-usage", "--disable-gpu");
        options.addArguments("--disable-save-password-bubble");
        options.addArguments("--disable-infobars");
        options.addArguments("--disable-notifications");
        options.addArguments("--start-maximized");
        options.addArguments("--incognito");
        options.addArguments("--disable-features=OverlayScrollbar");
        Map<String, Object> prefs = new HashMap<>();
       options.addArguments("user-agent=Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/142.0.0.0 Safari/537.36");
        options.addArguments("--disable-blink-features=AutomationControlled");
        options.setExperimentalOption("prefs", prefs);
       // options.addExtensions(new File("src/test/resources/AdBlock.crx"));
        options.addExtensions(new File("src/test/resources/Malware.crx"));

        driver = new ChromeDriver(options);

        // example timeouts; prefer explicit waits in tests
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(20));
        driver.manage().timeouts().scriptTimeout(Duration.ofSeconds(10));

        THREAD_DRIVER.set(driver);
        return driver;


        }


    // call from teardown to quit driver for current thread
    public static void quitDriver() {
        WebDriver driver = THREAD_DRIVER.get();
        if (driver != null) {
            try {
                driver.quit();
            } finally {
                THREAD_DRIVER.remove();
            }
        }
    }
}
