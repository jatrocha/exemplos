package br.com.cygnus.exemplos.apresentacao;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.cygnus.framework.exception.EngineRuntimeException;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

/**
 * Ponte para que o JQuery local possa ler resultado do servico disponivel no endpoint rest.
 */
public class CarroServlet extends HttpServlet {

   private static final String CONTENT_TYPE = "application/json";

   private static final String LIST_URL = "http://localhost:8088/rest-api/carro/query";

   private static final Integer STATUS_OK = Integer.valueOf(200);

   private static final long serialVersionUID = -5456444340239308484L;

   /**
    * @see javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse).
    */
   @Override
   protected void doGet(HttpServletRequest request, HttpServletResponse resp) throws ServletException, IOException {

      Client client = Client.create();

      WebResource resource = client.resource(LIST_URL);

      ClientResponse response = resource.accept(CONTENT_TYPE).put(ClientResponse.class);

      if (!Integer.valueOf(response.getStatus()).equals(STATUS_OK)) {

         throw new EngineRuntimeException("Failed : HTTP error code : " + response.getStatus());
      }

      resp.setContentType(CONTENT_TYPE);

      resp.getWriter().print(response.getEntity(String.class));

      resp.getWriter().flush();
   }

}
