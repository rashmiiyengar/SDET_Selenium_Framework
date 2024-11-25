package com.rash.tests;

import com.rash.tests.listeners.TestListener;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestContext;
import org.testng.annotations.*;
import util.ConfigUtil;
import util.Constants;

import java.net.MalformedURLException;
import java.net.URL;

@Listeners({TestListener.class})
public abstract class AbstractTest {

    protected WebDriver driver;

    @BeforeSuite
    public void setupConfig(){
        ConfigUtil.initialize();
    }

    @BeforeTest
    public void setDriver(ITestContext context) throws MalformedURLException {
     this.driver=  Boolean.parseBoolean(ConfigUtil.get(Constants.GRID_ENABLED)) ? getRemoteDriver() : getLocalDriver();
     context.setAttribute(Constants.DRIVER,this.driver);
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