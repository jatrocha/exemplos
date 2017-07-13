package br.com.cygnus.exemplos.frontend;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class FrontendHeadlessIT {

   private WebDriver browser;

   @Before
   public void setup() {

      this.browser = new FirefoxDriver();
   }

   @Test
   public void startTest() {
      this.browser.get("http://localhost:8081/examples/");

      this.browser.findElement(By.id("id")).click();

      // Will throw exception if elements not found
      this.browser.findElement(By.id("id")).sendKeys("011");

      this.browser.findElement(By.id("titulo")).sendKeys("Título");

      this.browser.findElement(By.id("autor")).sendKeys("Autor");

      this.browser.findElement(By.id("genero")).sendKeys("Gênero");

      this.browser.findElement(By.id("btnSubmit")).click();
      // this.browser.findElement(By.id("account")).click();

      // assertEquals("John", this.browser.findElement(By.id("firstName")).getAttribute("value"));

   }

   @After
   public void tearDown() {
      this.browser.quit();
   }
}
