package com.rash.pages.flightreservation;

import com.rash.pages.AbstractPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class FlightsSelectionPage extends AbstractPage {
    private final By departureFlightOptions = By.name("departure-flight");
    private final By arrivalFlightOptions = By.name("arrival-flight");
    private final By confirmFlightsButton = By.id("confirm-flights");

    public FlightsSelectionPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public boolean isAt() {

        try{
            WebElement confirmFlightButtonCheck=this.wait.until(ExpectedConditions.visibilityOfElementLocated(this.confirmFlightsButton));
           return confirmFlightButtonCheck.isDisplayed();
        }catch (Exception e){
            return false;
        }

    }

    public FlightsSelectionPage selectFlights(){
        List<WebElement> departureFlights = driver.findElements(departureFlightOptions);

        if(!departureFlights.isEmpty()){
            int randomDepartureIndex = ThreadLocalRandom.current().nextInt(0,departureFlights.size());
            //click on randomly selected departure flight
            departureFlights.get(randomDepartureIndex).click();
        }

        List<WebElement> arrivalFlights = driver.findElements(arrivalFlightOptions);

        if(!departureFlights.isEmpty()){
            int randomArrivalIndex = ThreadLocalRandom.current().nextInt(0,arrivalFlights.size());
            //click on randomly selected arrival flight
            departureFlights.get(randomArrivalIndex).click();
        }

        return this;
    }


    public FlightsConfirmationPage confirmFlights(){
        WebElement confirmButton=this.wait.until(ExpectedConditions.visibilityOfElementLocated(this.confirmFlightsButton));

        confirmButton.click();
        return new FlightsConfirmationPage(driver);
    }

}
