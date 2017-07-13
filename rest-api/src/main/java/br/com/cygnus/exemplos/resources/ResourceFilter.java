package br.com.cygnus.exemplos.resources;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

public class ResourceFilter implements Filter {

   private static final Logger logger = Logger.getLogger(ResourceFilter.class.getName());

   @Override
   public void init(FilterConfig filterConfig) throws ServletException {
      // TODO Auto-generated method stub

   }

   @Override
   public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

      logger.log(Level.INFO, "HTML5CorsFilter add HTML5 CORS Headers");

      HttpServletResponse res = (HttpServletResponse) response;

      res.addHeader("Access-Control-Allow-Origin", "*");

      res.addHeader("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT");

      res.addHeader("Access-Control-Allow-Headers", "Content-Type");

      chain.doFilter(request, response);

   }

   @Override
   public void destroy() {
      // TODO Auto-generated method stub

   }

}
