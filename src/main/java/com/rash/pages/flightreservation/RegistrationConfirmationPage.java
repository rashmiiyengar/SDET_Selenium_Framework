package com.rash.pages.flightreservation;

import com.rash.pages.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.By;

public class RegistrationConfirmationPage extends AbstractPage {

    private final By flightsSearchButton = By.id("go-to-flights-search");

    private WebElement waitForVisibility(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public RegistrationConfirmationPage(WebDriver driver){
        super(driver);
    }

    @Override
    public boolean isAt() {
        try {
            WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(this.flightsSearchButton));
            return element.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public RegistrationConfirmationPage clickFlightSearchButton() {
        waitForVisibility(flightsSearchButton).click();
        return this;
    }


}
