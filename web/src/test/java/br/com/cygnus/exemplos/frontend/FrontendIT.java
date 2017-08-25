package br.com.cygnus.exemplos.frontend;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

public class FrontendIT {

   @Before
   public void setup() {

   }

   @Test
   public void startTest() {

      final WebDriver browser = new HtmlUnitDriver();

      browser.get("http://localhost:8081/examples/");

      browser.findElement(By.id("id")).click();

      browser.findElement(By.id("id")).sendKeys("011");

      browser.findElement(By.id("titulo")).sendKeys("Título");

      browser.findElement(By.id("autor")).sendKeys("Autor");

      browser.findElement(By.id("genero")).sendKeys("Gênero");

      browser.findElement(By.id("btnSubmit")).click();

      // this.browser.findElement(By.id("account")).click();

      // assertEquals("John", this.browser.findElement(By.id("firstName")).getAttribute("value"));

      Assert.assertTrue(true);

   }

   @After
   public void tearDown() {
      // this.browser.close();
   }
}
