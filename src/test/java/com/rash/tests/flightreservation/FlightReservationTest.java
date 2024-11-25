package com.rash.tests.flightreservation;

import com.rash.pages.flightreservation.*;
import com.rash.tests.AbstractTest;
import com.rash.tests.flightreservation.model.FlightReservationTestData;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import util.ConfigUtil;
import util.Constants;
import util.JsonUtil;

public class FlightReservationTest extends AbstractTest {

    private FlightReservationTestData testData;

    @BeforeTest
    //@Parameters({"noOfPassengers","expectedPrice"})
    @Parameters("testDataPath")
    public void setParameters(String testDataPath){
        this.testData = JsonUtil.getTestData(testDataPath, FlightReservationTestData.class);
    }


    @Test
    public void userRegistrationTest(){
        RegistrationPage registrationPage= new RegistrationPage(driver);
        registrationPage.goTo(ConfigUtil.get(Constants.FLIGHT_RESERVATION_URL));
        Assert.assertTrue(registrationPage.isAt());
        registrationPage.enterUserDetails(testData.firstName(), testData.lastName()).
                enterUserCredential(testData.email(), testData.password()).
                enterAddress(testData.street(), testData.city(), testData.zip()).
                clickRegister();
    }

    @Test(dependsOnMethods = "userRegistrationTest")
    public void registrationConfirmationTest(){

        RegistrationConfirmationPage registrationConfirmationPage= new RegistrationConfirmationPage(driver);

        Assert.assertTrue(registrationConfirmationPage.isAt());
        registrationConfirmationPage.clickFlightSearchButton();

    }

    @Test(dependsOnMethods = "registrationConfirmationTest")
    public void flightsSearchTest(){
        FlightSearchPage flightSearchPage= new FlightSearchPage(driver);
        Assert.assertTrue(flightSearchPage.isAt());
        flightSearchPage.selectPassengers(testData.passengersCount()).
                searchFlights();
    }

    @Test(dependsOnMethods = "flightsSearchTest")
    public void flightsSelectionTest(){
        FlightsSelectionPage flightsSelectionPage= new FlightsSelectionPage(driver);
        Assert.assertTrue(flightsSelectionPage.isAt());
        flightsSelectionPage.selectFlights().
                confirmFlights();
    }

    @Test(dependsOnMethods = "flightsSelectionTest")
    public void flightConfirmationTest(){
        FlightsConfirmationPage flightsConfirmationPage= new FlightsConfirmationPage(driver);
        Assert.assertTrue(flightsConfirmationPage.isAt());
        Assert.assertEquals(flightsConfirmationPage.getPrice(),testData.expectedPrice());

    }

    @AfterTest
    public void quitDriver(){
        this.driver.quit();
    }
}
