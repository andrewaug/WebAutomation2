package com.amazon.tests;

import com.amazon.data.ExcelDataProvider;
import com.amazon.pages.HomePage;
import com.amazon.pages.LogoutPage;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Map;

public class HomePageTest extends BaseTest{


    @Test(dataProvider = "homepage",dataProviderClass= ExcelDataProvider.class, priority = 2)
    public void testcases_linkcheck(Map<String,Object> homePageData){
        HomePage homePage = page.getInstance(HomePage.class);
        Assert.assertTrue(homePage.clickTestCasesLink());
        Assert.assertEquals(homePage.getPageHeaderText(),homePageData.get("ExpectedResults"));

    }
}
