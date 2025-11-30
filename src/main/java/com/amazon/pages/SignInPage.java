package com.amazon.pages;

import com.amazon.base.BasePage;
import dev.failsafe.internal.util.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SignInPage extends BasePage {

    @FindBy(id = "username")
    private WebElement username;

    @FindBy(id = "password")
    private WebElement password;

    @FindBy(id = "submit-login")
    private WebElement login_button;

    @FindBy(xpath="//h1[contains(text(),'Test Login page for Automation Testing Practice')]")
    private WebElement signInPageHeader;

    public SignInPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver,this);

    }

    public HomePage signIN(String signinUsername, String signinPassword){

        getVisibleElement(username).sendKeys( signinUsername );
        getVisibleElement(password).sendKeys( signinPassword );
        safeClick( login_button );
        return getInstance(HomePage.class);

    }




}
