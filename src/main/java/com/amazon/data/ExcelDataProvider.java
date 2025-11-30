package com.amazon.data;

import com.amazon.utils.ExcelUtil;
import org.testng.annotations.DataProvider;

public class ExcelDataProvider {

    @DataProvider(name = "loginpage")
    public Object[][] getLoginPage( ){
       return  ExcelUtil.getTestData( "loginpage" );
    }

    @DataProvider(name = "logoutpage")
    public Object[][] getLogoutPage( ){
        return  ExcelUtil.getTestData( "logoutpage" );
    }

    @DataProvider(name = "homepage")
    public Object[][] getHomePage( ){
        return  ExcelUtil.getTestData( "homepage" );
    }

}
