package com.amazon.tests;

import com.amazon.base.BasePage;
import com.amazon.base.Page;
import com.amazon.driver.ChromeDriverProvider;
import com.amazon.utils.ConfigReader;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

import java.util.Properties;

public class BaseTest {
    protected WebDriver driver;
    public Page page;


    @BeforeMethod
    @Parameters(value={"browser"})
    public void baseSetup(String browser){
        switch(browser.toLowerCase()){
            case "chrome":
                this.driver = new ChromeDriverProvider().getDriver();
                Properties prop = ConfigReader.initProperties();
                if(driver==null){
                    System.out.print("driver=============");

                }
                this.driver.get( prop.getProperty("url") );
                break;

            case "edge":
                //driver = new EdgeDriver();
                break;

            default:
                throw new IllegalArgumentException("Unsupported browser: "+browser);

        }
        page =  new BasePage( driver );

    }

    @AfterMethod
    public void tearDown(){
        if(driver!=null){
            ChromeDriverProvider.quitDriver();
        }
    }
}
