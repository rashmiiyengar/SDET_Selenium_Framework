package com.rash.tests.flightreservation;

import com.rash.pages.flightreservation.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import util.ConfigUtil;
import util.Constants;

public class FlightReservationTest {

    private WebDriver driver;
    private String noOfPassengers;
    private String expectedPrice;

    @BeforeTest
    @Parameters({"noOfPassengers","expectedPrice"})
    public void setDriver(String noOfPassengers,String expectedPrice){
        this.noOfPassengers=noOfPassengers;
        this.expectedPrice=expectedPrice;
        //driver Setup
        WebDriverManager.chromedriver().setup();
        this.driver= new ChromeDriver();
    }

    @Test
    public void userRegistrationTest(){
        RegistrationPage registrationPage= new RegistrationPage(driver);
        registrationPage.goTo(ConfigUtil.get(Constants.FLIGHT_RESERVATION_URL));
        Assert.assertTrue(registrationPage.isAt());
        registrationPage.enterUserDetails("Selenium","docker").
                enterUserCredential("sdfb@gmail.com","sghrd").
                enterAddress("Mary Ave","Georgia","45567").
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
        flightSearchPage.selectPassengers(noOfPassengers).
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
        Assert.assertEquals(flightsConfirmationPage.getPrice(),expectedPrice);

    }

    @AfterTest
    public void quitDriver(){
        this.driver.quit();
    }
}
