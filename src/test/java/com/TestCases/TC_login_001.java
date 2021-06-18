package com.TestCases;

import com.Base.TestBase;
import com.Utilities.Xls_Reader;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class TC_login_001 extends TestBase {

    @BeforeClass
    public void setDriver() {
        launchFirefox();
        Reporter.log("Firefox Driver Launched");
    }

    @AfterClass
    public void closeDriver() {
        closeBrowser();
    }

    @Test
    public void loginTest() {

        openTestSite("https://demo.opencart.com/index.php?route=account/login");

        Reporter.log("Given site is open");

        //Excel Implementation
        Xls_Reader reader = new Xls_Reader("./src/main/resources/logindata.xlsx");
        String sheetName = "Sheet1";

        int rowCount = reader.getRowCount(sheetName);

        for (int rowNum = 2; rowNum <= rowCount; rowNum++) {
            String email = reader.getCellData(sheetName, "email", rowNum);
            String pswrd = reader.getCellData(sheetName, "password", rowNum);
            String input = reader.getCellData(sheetName, "input", rowNum);


            WebElement mail = driver.findElement(By.xpath("//*[@id=\"input-email\"]"));
            mail.clear();
            mail.sendKeys(email);

            Reporter.log(email + " email is given");

            WebElement pass = driver.findElement(By.xpath("//*[@id=\"input-password\"]"));
            pass.clear();
            pass.sendKeys(pswrd);

            Reporter.log(pswrd + "password is given");

            WebElement login = driver.findElement(By.xpath("//*[@id=\"content\"]/div/div[2]/div/form/input"));
            login.click();

            Reporter.log("login is clicked");

            String exp_title = "My Account";
            String act_title = driver.getTitle();


            if (input.equals("valid")) {
                if (exp_title.equals(act_title)) {
                    System.out.println("Test is PASSED for positive test!!");

                    //Report write in excel file
                    reader.setCellData(sheetName, "result", rowNum, "PASSED");

                    driver.findElement(By.xpath("//body/div[@id='account-account']/div[1]/aside[1]/div[1]/a[13]")).click();
                    driver.findElement(By.xpath("//body/div[@id='common-success']/div[1]/aside[1]/div[1]/a[1]")).click();

                } else {

                    System.out.println("Test is FAILED for positive test!!");
                    reader.setCellData(sheetName, "result", rowNum, "FAILED");

                }

            }
            if (input.equals("invalid")) {
                if (!exp_title.equals(act_title)) {

                    System.out.println("Test is passed for negative test!!");
                    reader.setCellData(sheetName, "result", rowNum, "PASSED");


                } else {

                    System.out.println("Test is failed for negative test!!");
                    reader.setCellData(sheetName, "result", rowNum, "FAILED");
                    driver.findElement(By.xpath("//body/div[@id='account-account']/div[1]/aside[1]/div[1]/a[13]")).click();
                    driver.findElement(By.xpath("//body/div[@id='common-success']/div[1]/aside[1]/div[1]/a[1]")).click();

                }
            }


        }
    }
}
