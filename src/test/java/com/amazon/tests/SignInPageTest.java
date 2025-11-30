package com.amazon.tests;

import com.amazon.data.ExcelDataProvider;
import com.amazon.pages.HomePage;
import com.amazon.pages.SignInPage;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Map;

public class SignInPageTest extends BaseTest{



    @Test(dataProvider = "loginpage", dataProviderClass = ExcelDataProvider.class,priority = 1)
    public void verifyLoginPageTitleTest(Map<String, Object> searchData){

        HomePage homePage  = page.getInstance ( SignInPage.class ).signIN(searchData.get("username").toString(), searchData.get("password").toString() );
        if(homePage.isHomePageLanded()) {
            Assert.assertEquals(homePage.getTitle(), searchData.get("ExpectedResults").toString());
        }
        //test.pass ( "Login Page Title was successfully verified" );
    }

}
