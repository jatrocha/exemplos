package br.com.cygnus.exemplos;

import org.apache.wicket.util.tester.WicketTester;
import org.junit.Before;
import org.junit.Test;

import br.com.cygnus.exemplos.HomePage;
import br.com.cygnus.exemplos.WicketApplication;

/**
 * Simple test using the WicketTester
 */
public class TestHomePage {
   private WicketTester tester;

   @Before
   public void setUp() {
      this.tester = new WicketTester(new WicketApplication());
   }

   @Test
   public void homepageRendersSuccessfully() {
      // start and render the test page
      this.tester.startPage(HomePage.class);

      // assert rendered page class
      this.tester.assertRenderedPage(HomePage.class);
   }
}
