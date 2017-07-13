package br.com.cygnus.exemplos;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.request.mapper.parameter.PageParameters;

public class HomePage extends WebPage {

   private static final long serialVersionUID = 1L;

   public HomePage(final PageParameters parameters) {

      super(parameters);

      this.add(new Label("version", this.getApplication().getFrameworkSettings().getVersion()));

      // TODO Add your page's components here

   }
}
