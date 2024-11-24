package com.rash.tests.vendorportal;

import com.rash.pages.venderportal.DashboardPage;
import com.rash.pages.venderportal.LoginPage;
import com.rash.tests.AbstractTest;
import com.rash.tests.vendorportal.model.VendorPortalTestData;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import util.ConfigUtil;
import util.Constants;
import util.JsonUtil;

public class VendorPortalTest extends AbstractTest {

    private LoginPage loginPage;
    private DashboardPage dashboardPage;
    private VendorPortalTestData testData;


    @BeforeTest
    @Parameters("testDataPath")
    public void setPageObjects(String testDataPath){
        this.loginPage=new LoginPage(driver);
        this.dashboardPage=new DashboardPage(driver);
        this.testData= JsonUtil.getTestData(testDataPath,VendorPortalTestData.class);
    }

    @Test
    public void loginTest(){
        LoginPage loginPage=new LoginPage(driver);
        loginPage.goTo(ConfigUtil.get(Constants.VENDOR_PORTAL_URL));
        Assert.assertTrue(loginPage.isAt());
        loginPage.login(testData.username(), testData.password());
    }

    @Test(dependsOnMethods = "loginTest")
    public void dashboardTest(){

        Assert.assertEquals(dashboardPage.getMonthlyEarning(),testData.monthlyEarning());
        Assert.assertEquals(dashboardPage.getAnnualEarning(),testData.annualEarning());
        Assert.assertEquals(dashboardPage.getAvailableInventry(),testData.availableInventory());
        Assert.assertEquals(dashboardPage.getProfitMargin(),testData.profitMargin());

        //order history search
        dashboardPage.searchOrderHistory(testData.searchKeyword());
        Assert.assertEquals(dashboardPage.getResultsCount(),testData.searchResultsCount());
    }

    @Test(dependsOnMethods = "dashboardTest")
    public void logoutTest(){
        dashboardPage.logout();
        Assert.assertTrue(loginPage.isAt());
    }

}
