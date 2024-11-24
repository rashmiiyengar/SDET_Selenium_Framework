package com.rash.pages.flightreservation;

import com.rash.pages.AbstractPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class RegistrationPage extends AbstractPage {

    // Locators
    private final By firstNameInput = By.id("firstName");
    private final By lastNameInput = By.id("lastName");
    private final By emailInput = By.id("email");
    private final By passwordInput = By.id("password");
    private final By streetInput = By.name("street");
    private final By cityInput = By.name("city");
    private final By zipInput = By.name("zip");
    private final By registerButton = By.id("register-btn");

    // Constructor
    public RegistrationPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public boolean isAt() {
        try {
            WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(registerButton));
            return element.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    // Actions
    public void goTo(String url) {
        driver.get(url);
    }

    public RegistrationPage enterUserDetails(String firstName, String lastName) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(firstNameInput)).sendKeys(firstName);
        wait.until(ExpectedConditions.visibilityOfElementLocated(lastNameInput)).sendKeys(lastName);
        return this;
    }

    public RegistrationPage enterUserCredential(String email, String password) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(emailInput)).sendKeys(email);
        wait.until(ExpectedConditions.visibilityOfElementLocated(passwordInput)).sendKeys(password);
        return this;
    }

    public RegistrationPage enterAddress(String street, String city, String zip) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(streetInput)).sendKeys(street);
        wait.until(ExpectedConditions.visibilityOfElementLocated(cityInput)).sendKeys(city);
        wait.until(ExpectedConditions.visibilityOfElementLocated(zipInput)).sendKeys(zip);
        return this;
    }

    public RegistrationPage clickRegister() {
        wait.until(ExpectedConditions.elementToBeClickable(registerButton)).click();
        return this;
    }
}
