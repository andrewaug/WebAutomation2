package com.amazon.tests;

import com.amazon.data.ExcelDataProvider;
import com.amazon.pages.HomePage;
import com.amazon.pages.LogoutPage;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Map;

public class LogoutPageTest extends BaseTest{

    @Test(dataProvider = "logoutpage",dataProviderClass= ExcelDataProvider.class, priority = 3)
    public void user_logout_test(Map<String,Object> logoutData){

        LogoutPage logoutPage = page.getInstance(LogoutPage.class);
        Assert.assertEquals( logoutPage.user_logout().getPageTitle(), logoutData.get("ExpectedResults") );

    }


}
