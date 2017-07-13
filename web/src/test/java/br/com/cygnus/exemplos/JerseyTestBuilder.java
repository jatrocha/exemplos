package br.com.cygnus.exemplos;

import static com.sun.jersey.api.json.JSONConfiguration.FEATURE_POJO_MAPPING;

import java.util.Collection;
import java.util.HashSet;

import org.codehaus.jackson.jaxrs.JacksonJsonProvider;

import br.com.cygnus.framework.exception.EngineExceptionMapper;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.client.filter.LoggingFilter;
import com.sun.jersey.api.core.DefaultResourceConfig;
import com.sun.jersey.api.json.JSONConfiguration;
import com.sun.jersey.test.framework.AppDescriptor;
import com.sun.jersey.test.framework.JerseyTest;
import com.sun.jersey.test.framework.LowLevelAppDescriptor;
import com.sun.jersey.test.framework.spi.client.ClientFactory;
import com.sun.jersey.test.framework.spi.container.TestContainerException;
import com.sun.jersey.test.framework.spi.container.TestContainerFactory;
import com.sun.jersey.test.framework.spi.container.grizzly2.GrizzlyTestContainerFactory;

public class JerseyTestBuilder {

   private final Collection<Object> resources = new HashSet<Object>();

   private Integer port = Integer.valueOf(8090);

   public static JerseyTestBuilder createJerseyTestBuilder() {

      return new JerseyTestBuilder().addResource(new EngineExceptionMapper());
   }

   public JerseyTestBuilder withPort(int port) {

      this.port = port;

      return this;
   }

   public JerseyTestBuilder addResource(Object resource) {

      this.resources.add(resource);

      return this;
   }

   public JerseyTest build() {

      return new EngineJerseyTest();
   }

   class EngineJerseyTest extends JerseyTest {

      @Override
      protected TestContainerFactory getTestContainerFactory() throws TestContainerException {

         return new GrizzlyTestContainerFactory();
      }

      @Override
      protected int getPort(int defaultPort) {

         if (JerseyTestBuilder.this.port == null) {

            return super.getPort(defaultPort);

         } else {

            return JerseyTestBuilder.this.port;
         }

      }

      @Override
      protected ClientFactory getClientFactory() {

         return new ClientFactory() {

            @Override
            public Client create(ClientConfig clientConfig) {

               clientConfig.getClasses().add(JacksonJsonProvider.class);
               clientConfig.getProperties().put(JSONConfiguration.FEATURE_POJO_MAPPING, Boolean.TRUE);

               Client client = Client.create(clientConfig);

               client.addFilter(new LoggingFilter());

               return client;
            }

         };

      }

      @Override
      protected AppDescriptor configure() {

         DefaultResourceConfig resourceConfig = new DefaultResourceConfig();

         resourceConfig.getFeatures().put(FEATURE_POJO_MAPPING, true);

         for (Object resource : JerseyTestBuilder.this.resources) {

            resourceConfig.getSingletons().add(resource);

         }

         ClientConfig clientConfig = new DefaultClientConfig();

         clientConfig.getFeatures().put(FEATURE_POJO_MAPPING, true);

         return new LowLevelAppDescriptor.Builder(resourceConfig).clientConfig(clientConfig).contextPath("rest-api").build();

      }
   }

}
