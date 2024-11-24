package com.rash.pages.flightreservation;

import com.rash.pages.AbstractPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

public class FlightSearchPage extends AbstractPage {
    private final By selectPassengers = By.id("passengers");
    private final By searchFlightsButton = By.id("search-flights");

    public FlightSearchPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public boolean isAt() {
        try {
         WebElement  element=this.wait.until(ExpectedConditions.visibilityOfElementLocated(this.selectPassengers));
            return element.isDisplayed();
        }catch (Exception e){
            return false;
        }
    }

    public FlightSearchPage selectPassengers(String noOfPassengers){
        WebElement  passengersDropdown = this.wait.until(ExpectedConditions.visibilityOfElementLocated(this.selectPassengers));

        Select select= new Select(passengersDropdown);

        select.selectByValue(noOfPassengers);
        return this;
    }

    public FlightSearchPage searchFlights(){
        WebElement searchButton = this.wait.until(ExpectedConditions.elementToBeClickable(this.searchFlightsButton));

        searchButton.click();
        return this;
    }

}
