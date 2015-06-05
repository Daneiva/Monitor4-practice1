package com.rmanager.tesng;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;


import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;

import org.junit.*;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AddLocation {
  private WebDriver driver;
  private String baseUrl;
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();
  

  @BeforeTest
  public void setUp() throws Exception {
    driver = new FirefoxDriver();
    baseUrl = "http://172.20.208.174:4044/";
    driver.manage().window().maximize();
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
  }

  @Test(priority = 0)
  public void testAddLocation2() throws Exception {
	  
	  String locationTest = "LocationTest800"; 
	  
    driver.get(baseUrl + "/admin/#/login");
    driver.findElement(By.xpath("//button")).click();
    (new WebDriverWait(driver,60)).until(ExpectedConditions.presenceOfElementLocated(By.linkText("Locations")));

    driver.findElement(By.linkText("Locations")).click();
    driver.findElement(By.xpath("//div[4]/div/button")).click();
    driver.findElement(By.id("location-add-name")).clear();
    driver.findElement(By.id("location-add-name")).sendKeys(locationTest);
    driver.findElement(By.id("location-add-display-name")).click();
    driver.findElement(By.id("location-add-display-name")).clear();
    driver.findElement(By.id("location-add-display-name")).sendKeys(locationTest);
    driver.findElement(By.cssSelector("button.btn.btn-primary")).click();
    
    (new WebDriverWait(driver,60)).until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("div.ng-binding.ng-scope")));

    assertEquals("Location successfully added", driver.findElement(By.cssSelector("div.ng-binding.ng-scope")).getText());
  }

  @AfterTest
  public void tearDown() throws Exception {
    driver.quit();
    String verificationErrorString = verificationErrors.toString();
    if (!"".equals(verificationErrorString)) {
      fail(verificationErrorString);
    }
  }

  private boolean isElementPresent(By by) {
    try {
      driver.findElement(by);
      return true;
    } catch (NoSuchElementException e) {
      return false;
    }
  }

  private boolean isAlertPresent() {
    try {
      driver.switchTo().alert();
      return true;
    } catch (NoAlertPresentException e) {
      return false;
    }
  }

  private String closeAlertAndGetItsText() {
    try {
      Alert alert = driver.switchTo().alert();
      String alertText = alert.getText();
      if (acceptNextAlert) {
        alert.accept();
      } else {
        alert.dismiss();
      }
      return alertText;
    } finally {
      acceptNextAlert = true;
    }
  }
}
