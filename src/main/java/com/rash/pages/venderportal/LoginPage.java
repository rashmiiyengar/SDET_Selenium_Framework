package com.rash.pages.venderportal;

import com.rash.pages.AbstractPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class LoginPage extends AbstractPage {
    private final By usernameInput = By.id("username");
    private final By passwordInput = By.id("password");
    private final By loginButton = By.id("login");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public boolean isAt() {
        try{
            WebElement userNameVisibility=this.wait.until(ExpectedConditions.visibilityOfElementLocated(this.usernameInput));
            return userNameVisibility.isDisplayed();
        }catch (Exception e){
            return false;
        }
    }

    public void goTo(String url){
    this.driver.get(url);
    }

    public LoginPage login(String username,String password){
        wait.until(ExpectedConditions.visibilityOfElementLocated(this.usernameInput)).sendKeys(username);
        wait.until(ExpectedConditions.visibilityOfElementLocated(this.passwordInput)).sendKeys(password);
        wait.until(ExpectedConditions.elementToBeClickable(this.loginButton)).click();

        return this;


    }

}
