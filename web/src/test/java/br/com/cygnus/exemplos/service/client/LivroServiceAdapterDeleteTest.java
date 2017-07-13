package br.com.cygnus.exemplos.service.client;

import static br.com.cygnus.exemplos.commons.helper.MensagemHelper.EXCEPTION_DEVERIA_TER_SIDO_LANCADA;
import static br.com.cygnus.exemplos.commons.helper.MensagemHelper.EXCEPTION_NAO_DEVERIA_TER_SIDO_LANCADA;
import static br.com.cygnus.exemplos.commons.helper.MensagemHelper.MENSAGEM_ERRO_PADRAO_PARA_EXCEPTIONS;
import static junit.framework.Assert.fail;
import static org.junit.Assert.assertEquals;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.lib.legacy.ClassImposteriser;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import br.com.cygnus.exemplos.JerseyTestBuilder;
import br.com.cygnus.exemplos.business.impl.LivroBusiness;
import br.com.cygnus.exemplos.commons.dto.LivroDTO;
import br.com.cygnus.exemplos.commons.dto.LivroFilterDTO;
import br.com.cygnus.exemplos.resources.LivroResource;
import br.com.cygnus.framework.exception.EngineRuntimeException;

import com.sun.jersey.test.framework.JerseyTest;

public class LivroServiceAdapterDeleteTest {

   private final LivroResource resource = new LivroResource();

   private LivroServiceAdapter adapter;

   private final JerseyTest server = JerseyTestBuilder.createJerseyTestBuilder().addResource(this.resource).build();

   private Mockery context;

   @Before
   public void init() throws Exception {

      this.adapter = new LivroServiceAdapter();

      this.server.setUp();

      this.adapter.setWebResource(this.server.resource());

      this.context = new Mockery() {

         {

            this.setImposteriser(ClassImposteriser.INSTANCE);
         }

      };
   }

   @After
   public void tearDown() throws Exception {

      this.server.tearDown();
   }

   @Test
   public void testDeleteQuandoErroGeral() {

      final LivroBusiness livroBusinessMock = this.context.mock(LivroBusiness.class);

      this.context.checking(new Expectations() {

         {

            this.one(livroBusinessMock).delete(this.with(any(LivroDTO.class)));

            this.will(throwException(new EngineRuntimeException(MENSAGEM_ERRO_PADRAO_PARA_EXCEPTIONS)));
         }

      });

      this.resource.setBusiness(livroBusinessMock);

      try {

         this.adapter.delete(LivroFilterDTO.buildWith("1"));

         fail(EXCEPTION_DEVERIA_TER_SIDO_LANCADA);

      } catch (EngineRuntimeException e) {

         assertEquals(MENSAGEM_ERRO_PADRAO_PARA_EXCEPTIONS, e.getErrors().iterator().next().getDescription());
      }

      this.context.assertIsSatisfied();
   }

   @Test
   public void testUpdate() {

      final LivroBusiness livroBusinessMock = this.context.mock(LivroBusiness.class);

      this.context.checking(new Expectations() {

         {

            this.one(livroBusinessMock).delete(this.with(any(LivroDTO.class)));
         }

      });

      this.resource.setBusiness(livroBusinessMock);

      try {

         this.adapter.delete(LivroFilterDTO.buildWith("1"));

      } catch (EngineRuntimeException e) {

         fail(EXCEPTION_NAO_DEVERIA_TER_SIDO_LANCADA);
      }

      this.context.assertIsSatisfied();
   }

}
