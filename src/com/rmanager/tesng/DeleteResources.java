package com.rmanager.tesng;

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
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
/*comentario */
public class DeleteResources {
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

  @Test(priority = 2)
  public void testDeleteResourse() throws Exception {
	String resource = "RESOURCE7001";   
	  
    driver.get(baseUrl + "/admin/#/login");
    driver.findElement(By.xpath("//button")).click();
    driver.findElement(By.linkText("Resources")).click();
    driver.findElement(By.xpath("//input[@type='text']")).clear();
    driver.findElement(By.xpath("//input[@type='text']")).sendKeys(resource);
    driver.findElement(By.cssSelector("input.ngSelectionCheckbox")).click();
    driver.findElement(By.id("btnRemove")).click();
    driver.findElement(By.cssSelector("button.btn-clear")).click();   
    assertEquals(resource, driver.findElement(By.cssSelector("div.ng-scope > span.ng-binding")).getText());
    driver.findElement(By.id("btnRemove")).click();
    assertEquals("There are no rooms associated to this resource", driver.findElement(By.cssSelector("div.alert.alert-info")).getText());
    driver.findElement(By.cssSelector("button.info")).click();
    driver.findElement(By.xpath("//input[@type='text']")).clear();
    driver.findElement(By.xpath("//input[@type='text']")).sendKeys(resource);
    (new WebDriverWait(driver,60)).until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("div.ng-scope > span.ng-binding")));
    System.out.print(driver.findElement(By.cssSelector("div.ng-scope > span.ng-binding")).getText());
    assertEquals("The resource was not removed",resource, driver.findElement(By.cssSelector("div.ng-scope > span.ng-binding")).getText());

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
