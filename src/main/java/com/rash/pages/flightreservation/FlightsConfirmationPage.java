package com.rash.pages.flightreservation;

import com.rash.pages.AbstractPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class FlightsConfirmationPage extends AbstractPage {

    private static final Logger log= LoggerFactory.getLogger(FlightsConfirmationPage.class);
    private final By totalPriceElement = By.cssSelector("#flights-confirmation-section .card-body .row:nth-child(3) .col:nth-child(2)");

    public FlightsConfirmationPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public boolean isAt() {
        try{
            WebElement priceElementCheck=this.wait.until(ExpectedConditions.visibilityOfElementLocated(this.totalPriceElement));
            return priceElementCheck.isDisplayed();
        }catch (Exception e){
            return false;
        }
    }

    public String getPrice(){
        WebElement totalPrice=this.wait.until(ExpectedConditions.visibilityOfElementLocated(this.totalPriceElement));
        log.info("Total price# : {}" , totalPrice.getText());
        return totalPrice.getText();

    }


}
