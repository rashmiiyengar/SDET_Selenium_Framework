package com.rash.pages.venderportal;

import com.rash.pages.AbstractPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DashboardPage extends AbstractPage {

    private static final Logger log= LoggerFactory.getLogger(DashboardPage.class);
    private final By monthlyEarningText = By.id("monthly-earning");
    private final By annualEarningText = By.id("annual-earning");
    private final By availableInventoryText = By.id("available-inventory");
    private final By profitMarginText = By.id("profit-margin");
    private final By searchBoxInput= By.xpath("//input[@class='form-control form-control-sm']");
    private final By searchResultsCountElement = By.id("dataTable_info");
    private final By userProfileDropDown = By.cssSelector("img.img-profile");
    private final By logoutLink = By.linkText("Logout");
    private final By modalLogoutButton = By.cssSelector("#logoutModal a");

    public DashboardPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public boolean isAt() {
        try{
            WebElement monthlyElementVisibility=this.wait.until(ExpectedConditions.visibilityOfElementLocated(this.monthlyEarningText));
            return monthlyElementVisibility.isDisplayed();
        }catch (Exception e){
            return false;
        }
    }

    public String getMonthlyEarning() {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(this.monthlyEarningText)).getText();
        }catch (Exception e){
            System.out.println("Element monthlyEarningText was not visible within the timeout period.");
            return null;
        }
    }

    public String getAnnualEarning() {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(this.annualEarningText)).getText();
        }catch (Exception e){
            System.out.println("Element annualEarningText was not visible within the timeout period.");
            return null;
        }
    }

    public String getAvailableInventry() {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(this.availableInventoryText)).getText();
        }catch (Exception e){
            System.out.println("Element Available inventary text was not visible within the timeout period.");
            return null;
        }
    }

    public String getProfitMargin() {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(this.profitMarginText)).getText();
        }catch (Exception e){
            System.out.println("Element profitMargin text was not visible within the timeout period.");
            return null;
        }
    }

    public DashboardPage searchOrderHistory(String keyword){
        sendKeysWhenVisible(this.searchBoxInput,keyword);
        return  this;
    }

    /*
    Showing 1 to 10 of 32 entries (filtered from 99 entries)
    arr[0]="Showing
    arr[1]="1"
    arr[2]="to"
    arr[3]="10"
    arr[4]="of"
    arr[5]="32"
     */
    public int getResultsCount(){

        String resultText = wait.until(ExpectedConditions.visibilityOfElementLocated(this.searchResultsCountElement)).getText();
        String[] arr= resultText.split(" ");
        int count =Integer.parseInt(arr[5]);
        log.info("Results count:{}",count);
        return count;
    }

    public void logout(){

        clickWhenVisible(this.userProfileDropDown);
        clickWhenVisible(this.logoutLink);
        clickWhenVisible(this.modalLogoutButton);

    }

}
