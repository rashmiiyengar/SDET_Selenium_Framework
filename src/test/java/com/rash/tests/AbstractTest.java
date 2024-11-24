package com.rash.tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import util.ConfigUtil;
import util.Constants;

import java.net.MalformedURLException;
import java.net.URL;

public abstract class AbstractTest {

    protected WebDriver driver;

    @BeforeSuite
    public void setupConfig(){
        ConfigUtil.initialize();
    }


    @BeforeTest
    @Parameters({"browser"})
    public void setDriver(String browser) throws MalformedURLException {
     this.driver=  Boolean.parseBoolean(ConfigUtil.get(Constants.GRID_ENABLED)) ? getRemoteDriver() : getLocalDriver();
    }

    private WebDriver getRemoteDriver() throws MalformedURLException {
        //http/localhost:4444/wd/hub , this is the path to be used to send request
        Capabilities capabilities =new ChromeOptions();
        if(Constants.FIREFOX.equalsIgnoreCase(ConfigUtil.get(Constants.BROWSER))){
            capabilities =new FirefoxOptions();
        }
        String urlFormat= ConfigUtil.get(Constants.GRID_URL_FORMAT);
        String hubHost= ConfigUtil.get(Constants.GRID_HUB_HOST);
        String url = String.format(urlFormat,hubHost);
        return new RemoteWebDriver(new URL(url),capabilities);
    }

    private WebDriver getLocalDriver(){
        WebDriverManager.chromedriver().setup();
        return new ChromeDriver();
    }

    @AfterTest
    public void quitDriver(){
        if (this.driver != null) {
            this.driver.quit();
        }
    }

}