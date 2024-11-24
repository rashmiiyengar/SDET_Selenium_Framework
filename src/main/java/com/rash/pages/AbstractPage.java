package com.rash.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public abstract class AbstractPage {

    protected final WebDriver driver;
    protected final WebDriverWait wait;

    public AbstractPage(WebDriver driver){

        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        PageFactory.initElements(driver,this);
    }

    public abstract boolean isAt();

    // Common Helper Method for Clicking
    protected void clickWhenVisible(By locator) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator)).click();
    }

    // Helper Method for Sending Keys
    protected void sendKeysWhenVisible(By locator, String text) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator)).sendKeys(text);
    }
}
