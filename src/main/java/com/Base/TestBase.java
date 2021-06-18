package com.Base;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.File;
import java.io.IOException;

public class TestBase {

    public static WebDriver driver;

    public static void main(String[] args) throws InterruptedException, IOException {
        launchChrome();
        closeBrowser();
        launchFirefox();
    }

    public static void launchChrome() {
        System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "//src//main//resources//chromedriver.exe");
        driver = new ChromeDriver();
    }

    public static void launchFirefox() {
        System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir") + "//src//main//resources//geckodriver.exe");
        driver = new FirefoxDriver();
    }

    public static void openTestSite(String url){

        driver.get(url);

    }

    public static void closeBrowser() {

        driver.quit();
    }

    public static WebElement getElementByXpath(String locator) {

        return driver.findElement(By.xpath(locator));

    }

    public static WebElement getElementByID(String locator) {

        return driver.findElement(By.id(locator));

    }

    public static WebElement getElementByName(String locator) {

        return driver.findElement(By.name(locator));

    }

    public static WebElement getElementByCSS(String locator) {

        return driver.findElement(By.cssSelector(locator));

    }

    public static void takePageScreenShot(WebDriver driver, String name) throws IOException {

        TakesScreenshot ts = (TakesScreenshot) driver;
        File src = ts.getScreenshotAs(OutputType.FILE);
        File trg = new File(System.getProperty("user.dir") + "//ScreenShots//" + name + ".png");
        FileUtils.copyFile(src, trg);

    }

    public static void takeElementScreenShot(WebDriver driver, WebElement element, String name) throws IOException {

        File src = element.getScreenshotAs(OutputType.FILE);
        File trg = new File(System.getProperty("user.dir") + "//ScreenShots//" + name + ".png");
        FileUtils.copyFile(src, trg);


    }


}


