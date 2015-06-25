package com.mycompany.uftutorial.integration;

import static org.junit.Assert.*;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

public class SmokeTest {
  private WebDriver driver;
  private String baseUrl;

  @Before
  public void setUp() throws Exception {
    driver = new FirefoxDriver();
    baseUrl = "http://localhost:8080/";
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    driver.manage().window().maximize();
  }

  @After
  public void tearDown() throws Exception {
    driver.quit();
  }

  @Test
  public void testSmoke() throws Exception {
    driver.get(baseUrl + "/uberfire-tutorial/");
    driver.findElement(By.name("uf_username")).clear();
    driver.findElement(By.name("uf_username")).sendKeys("admin");
    driver.findElement(By.name("uf_password")).clear();
    driver.findElement(By.name("uf_password")).sendKeys("admin");
    driver.findElement(By.cssSelector("input.button")).click();

    waitForText("Hello, admin. Welcome to UberFire!", By.cssSelector("div.gwt-Label"));

    driver.findElement(By.cssSelector("input.gwt-TextBox")).clear();
    driver.findElement(By.cssSelector("input.gwt-TextBox")).sendKeys("testy");
    driver.findElement(By.cssSelector("input.gwt-TextBox")).sendKeys(Keys.RETURN);

    assertEquals("I understand you are feeling testy", driver.findElement(By.cssSelector("div.gwt-Label")).getText());

    assertEquals("Screens", driver.findElement(By.linkText("Screens")).getText());
    assertEquals("Other", driver.findElement(By.linkText("Other")).getText());

    driver.findElement(By.cssSelector("i.icon-asterisk")).click();
    assertEquals("Hello, admin. Welcome to UberFire!", driver.findElement(By.cssSelector("div.gwt-Label")).getText());

    // test perspective switching
    WebElement perspectivesMenu = driver.findElement(By.linkText("Perspectives"));
    perspectivesMenu.click();
    WebElement horizontalPerspective = driver.findElement(By.linkText("Horizontal Perspective"));
    horizontalPerspective.click();

    WebElement horizNorthPanel = driver.findElement(By.id("horizontal-northPanel"));
    WebElement horizRootPanel = driver.findElement(By.id("horizontal-rootPanel"));

    // make sure the horizontal perspective is actually horizontal!
    assertTrue(horizNorthPanel.getSize().width > horizNorthPanel.getSize().height);
    assertTrue(horizRootPanel.getSize().width > horizRootPanel.getSize().height);
  }

  private void waitForText(String text, By by) throws InterruptedException {
    for (int second = 0;; second++) {
      if (second >= 60) {
        fail("timeout");
      }
      if (text.equals(driver.findElement(by).getText())) {
        break;
      }
      Thread.sleep(1000);
    }
  }

  private void assertElementPresent(By by) {
    assertEquals(1, driver.findElements(by).size());
  }
}
