package br.com.cygnus.exemplos.service.client;

import static br.com.cygnus.exemplos.commons.helper.MensagemHelper.EXCEPTION_DEVERIA_TER_SIDO_LANCADA;
import static br.com.cygnus.exemplos.commons.helper.MensagemHelper.MENSAGEM_ERRO_PADRAO_PARA_EXCEPTIONS;
import static junit.framework.Assert.fail;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.lib.legacy.ClassImposteriser;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import br.com.cygnus.exemplos.JerseyTestBuilder;
import br.com.cygnus.exemplos.business.LivroBusinessStub;
import br.com.cygnus.exemplos.business.impl.LivroBusiness;
import br.com.cygnus.exemplos.commons.dto.LivroDTO;
import br.com.cygnus.exemplos.commons.dto.LivroFilterDTO;
import br.com.cygnus.exemplos.resources.LivroResource;
import br.com.cygnus.framework.exception.EngineRuntimeException;

import com.sun.jersey.test.framework.JerseyTest;

public class LivroServiceAdapterReadTest {

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

      this.context = null;
   }

   @Test
   public void testReadQuandoErroGeral() {

      final LivroBusiness livroBusinessMock = this.context.mock(LivroBusiness.class);

      this.context.checking(new Expectations() {

         {

            this.one(livroBusinessMock).read(this.with(any(LivroFilterDTO.class)));

            this.will(throwException(new EngineRuntimeException(MENSAGEM_ERRO_PADRAO_PARA_EXCEPTIONS)));
         }

      });

      this.resource.setBusiness(livroBusinessMock);

      try {

         this.adapter.read(LivroFilterDTO.buildWith("1"));

         fail(EXCEPTION_DEVERIA_TER_SIDO_LANCADA);

      } catch (EngineRuntimeException e) {

         assertEquals(MENSAGEM_ERRO_PADRAO_PARA_EXCEPTIONS, e.getErrors().iterator().next().getDescription());
      }

      this.context.assertIsSatisfied();
   }

   @Test
   public void testReadQuandoLivroInexistente() {

      final LivroBusiness livroBusinessMock = this.context.mock(LivroBusiness.class);

      this.context.checking(new Expectations() {

         {

            this.one(livroBusinessMock).read(this.with(any(LivroFilterDTO.class)));

            this.will(returnValue(new LivroDTO()));
         }

      });

      this.resource.setBusiness(livroBusinessMock);

      LivroDTO livro = this.adapter.read(LivroFilterDTO.buildWith("1"));

      assertNotNull(livro);

      assertNull(livro.getId());

      this.context.assertIsSatisfied();
   }

   @Test
   public void testRead() {

      this.resource.setBusiness(new LivroBusinessStub());

      LivroDTO livro = this.adapter.read(LivroFilterDTO.buildWith("1"));

      assertNotNull(livro);

      assertEquals("id1", livro.getId());

      assertEquals("titulo1", livro.getTitulo());

      assertEquals("autor1", livro.getAutor());

      assertEquals("genero1", livro.getGenero());
   }

}
