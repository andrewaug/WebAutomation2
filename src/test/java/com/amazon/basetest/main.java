package com.amazon.basetest;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class main {

    public static void main(String []args){

        //B b = new B();

        B b1 = new B("test");


    }

    public void checkElement(){
        try{
            By locator = By.id("submit");

            WebDriver driver = new ChromeDriver();
            WebDriverManager.chromedriver().setup();
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

            wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
            WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
        	element.click();
	}
	catch(TimeoutException e){
            System.out.println("print message"+e.getMessage());
	}
    }
}
