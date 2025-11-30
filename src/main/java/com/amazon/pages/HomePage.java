package com.amazon.pages;

import com.amazon.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage extends BasePage {

    public HomePage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath="//a/i[contains(text(),'Logout')]")
    private WebElement logoutButton;

    @FindBy(xpath = "//h1")
    private WebElement testCaseHeader;

    @FindBy(xpath="//a[@href='/test-cases']")
    private WebElement testcaseslink;

    public String getHomePageTitle() {
        return super.getPageTitle();
    }

    public boolean isHomePageLanded(){
        return isElementDisplayed(logoutButton);

    }

    public boolean clickTestCasesLink() {
        return safeClick(testcaseslink);
    }

    public String getPageHeaderText() {
        return testCaseHeader.getText();
    }




}
