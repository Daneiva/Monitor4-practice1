package com.rmanager.tesng;

import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;

import org.junit.*;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;


public class UpdatedCapRoom {
  private WebDriver driver;
  private String baseUrl;
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();

  @BeforeTest
  public void setUp() throws Exception {
    driver = new FirefoxDriver();
    baseUrl = "http://172.20.208.174:4042/";
    driver.manage().window().maximize();
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
  }

  @Test(priority = 4)
  public void testSearchRoom1() throws Exception {
	  String room = "SM-Room10";
	  String cap = "1232";
	  
    driver.get(baseUrl + "/admin/#/login");
    driver.findElement(By.xpath("//button")).click();
    driver.findElement(By.linkText("Conference Rooms")).click();
    (new WebDriverWait(driver,60)).until(ExpectedConditions.presenceOfElementLocated(By.linkText("Conference Rooms")));

    driver.findElement(By.xpath("//input[@type='text']")).clear();
    driver.findElement(By.xpath("//input[@type='text']")).sendKeys(room);
    
    (new WebDriverWait(driver,60)).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@id='roomsGrid']/div[2]/div/div/div[3]/div[2]/div/span[2]")));
    
    driver.findElement(By.xpath("//input[@type='text']")).clear();
    driver.findElement(By.xpath("//input[@type='text']")).sendKeys(room);
    
    assertEquals(room, driver.findElement(By.xpath("//div[@id='roomsGrid']/div[2]/div/div/div[3]/div[2]/div/span[2]")).getText());
        
    Actions action = new Actions(driver);
    action.moveToElement(driver.findElement(By.xpath("//div[@id='roomsGrid']/div[2]/div/div/div[3]/div[2]/div/span[2]"))).doubleClick().build().perform();

    //driver.findElement(By.xpath("//div[@id='roomsGrid']/div[2]/div/div/div[3]/div[2]/div/span[2]")).click();
    driver.findElement(By.xpath("(//input[@type='text'])[5]")).clear();
    
    driver.findElement(By.xpath("(//input[@type='text'])[5]")).sendKeys(cap);
    driver.findElement(By.xpath("(//input[@type='text'])[6]")).clear();
    driver.findElement(By.xpath("(//input[@type='text'])[6]")).sendKeys(cap);
    driver.findElement(By.cssSelector("span.input-group-btn > button.btn.btn-default")).click();
    driver.findElement(By.xpath("(//button[@type='button'])[6]")).click();
    driver.findElement(By.cssSelector("button.info")).click();
    
    (new WebDriverWait(driver,60)).until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("div.ng-binding.ng-scope")));

    assertEquals("Room successfully Modified", driver.findElement(By.xpath("//div[2]/div")).getText());
        
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
