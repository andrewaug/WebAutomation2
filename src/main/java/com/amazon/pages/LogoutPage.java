package com.amazon.pages;

import com.amazon.base.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LogoutPage extends BasePage {


    public LogoutPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver,this);

    }

    @FindBy (xpath = "//li[contains(text(),'Login Page')]")
    private WebElement logoutMessage;

    @FindBy(xpath="//a/i[contains(text(),'Logout')]")
    private WebElement logoutButton;

    private static final String LOGOUT_URL = "https://practice.expandtesting.com/secure";


    public String getHomePageTitle() {
        return super.getPageTitle();
    }

    public boolean isHomePageLanded(){
        return isElementDisplayed(logoutButton);

    }

    public void navigate_to_Logout(){
        navigateToUrl( LOGOUT_URL );
    }
    public boolean isLogoutMessageDisplayed(){
        return logoutMessage.isDisplayed();
    }

    public SignInPage user_logout(){
        navigate_to_Logout();
        safeClick( logoutButton );
        return getInstance( SignInPage.class );
    }
}
