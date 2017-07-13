package br.com.cygnus.exemplos.service.client;

import static br.com.cygnus.exemplos.commons.helper.MensagemHelper.EXCEPTION_DEVERIA_TER_SIDO_LANCADA;
import static br.com.cygnus.exemplos.commons.helper.MensagemHelper.MENSAGEM_ERRO_PADRAO_PARA_EXCEPTIONS;
import static junit.framework.Assert.fail;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

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
import br.com.cygnus.exemplos.resources.LivroResource;
import br.com.cygnus.framework.IObjetoGenerico;
import br.com.cygnus.framework.exception.EngineRuntimeException;

import com.sun.jersey.test.framework.JerseyTest;

public class LivroServiceAdapterFindAllTest {

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
   public void testListQuandoErroGeral() {

      final LivroBusiness livroBusiness = this.context.mock(LivroBusiness.class);

      this.context.checking(new Expectations() {

         {

            this.one(livroBusiness).findAll();

            this.will(throwException(new EngineRuntimeException(MENSAGEM_ERRO_PADRAO_PARA_EXCEPTIONS)));
         }

      });

      this.resource.setBusiness(livroBusiness);

      try {

         this.adapter.findAll();

         fail(EXCEPTION_DEVERIA_TER_SIDO_LANCADA);
      } catch (EngineRuntimeException e) {

         assertEquals(MENSAGEM_ERRO_PADRAO_PARA_EXCEPTIONS, e.getErrors().iterator().next().getDescription());
      }

      this.context.assertIsSatisfied();
   }

   @Test
   public void testListQuandoNaoHouverLivros() {

      final LivroBusiness livroBusiness = this.context.mock(LivroBusiness.class);

      this.context.checking(new Expectations() {

         {

            this.one(livroBusiness).findAll();

            this.will(returnValue(new ArrayList<LivroDTO>(IObjetoGenerico.NUMERO_INTEIRO_ZERO)));
         }

      });

      this.resource.setBusiness(livroBusiness);

      List<LivroDTO> resultado = this.adapter.findAll();

      assertNotNull(resultado);

      assertTrue(resultado.isEmpty());

      this.context.assertIsSatisfied();
   }

   @Test
   public void testList() {

      this.resource.setBusiness(new LivroBusinessStub());

      List<LivroDTO> resultado = this.adapter.findAll();

      assertNotNull(resultado);

      assertEquals(Integer.valueOf(3), Integer.valueOf(resultado.size()));

      LivroDTO livro = resultado.get(1);

      assertEquals("id2", livro.getId());

      assertEquals("titulo2", livro.getTitulo());

      assertEquals("autor2", livro.getAutor());

      assertEquals("genero2", livro.getGenero());
   }

}
