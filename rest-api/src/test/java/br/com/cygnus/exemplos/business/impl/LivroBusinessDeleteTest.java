package br.com.cygnus.exemplos.business.impl;

import static br.com.cygnus.exemplos.commons.helper.MensagemHelper.EXCEPTION_DEVERIA_TER_SIDO_LANCADA;
import static br.com.cygnus.exemplos.commons.helper.MensagemHelper.MENSAGEM_ERRO_PADRAO_PARA_EXCEPTIONS;
import static junit.framework.Assert.fail;
import static org.junit.Assert.assertEquals;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.lib.legacy.ClassImposteriser;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import br.com.cygnus.exemplos.persistence.repository.LivroRepository;
import br.com.cygnus.framework.exception.EngineRuntimeException;

public class LivroBusinessDeleteTest extends LivroBusinessTestBase {

   private Mockery context;

   @Before
   public void init() throws Exception {

      this.context = new Mockery() {

         {

            this.setImposteriser(ClassImposteriser.INSTANCE);
         }

      };
   }

   @After
   public void tearDown() throws Exception {

      this.context = null;
   }

   @Test(expected = IllegalArgumentException.class)
   public void testDeleteQuandoParametroInvalidoNull() {

      new LivroBusiness().delete(null);
   }

   @Test(expected = IllegalArgumentException.class)
   public void testDeleteQuandoIdInvalidoNull() {

      new LivroBusiness().delete(this.LIVRO_VAZIO);
   }

   @Test(expected = IllegalArgumentException.class)
   public void testDeleteQuandoIdInvalidoVazio() {

      new LivroBusiness().delete(this.LIVRO_COM_ID_VAZIO);
   }

   @Test
   public void testDeleteQuandoErroGeral() {

      final LivroRepository repositoryMock = this.context.mock(LivroRepository.class);

      this.context.checking(new Expectations() {

         {

            this.one(repositoryMock).delete(LivroBusinessDeleteTest.this.ID);

            this.will(throwException(new EngineRuntimeException(MENSAGEM_ERRO_PADRAO_PARA_EXCEPTIONS)));
         }

      });

      try {

         new LivroBusiness(repositoryMock).delete(this.LIVRO_COM_ID);

         fail(EXCEPTION_DEVERIA_TER_SIDO_LANCADA);

      } catch (EngineRuntimeException e) {

         assertEquals(MENSAGEM_ERRO_PADRAO_PARA_EXCEPTIONS, e.getErrors().iterator().next().getDescription());
      }

      this.context.assertIsSatisfied();
   }

   @Test
   public void testDelete() {

      final LivroRepository repositoryMock = this.context.mock(LivroRepository.class);

      this.context.checking(new Expectations() {

         {

            this.one(repositoryMock).delete(LivroBusinessDeleteTest.this.ID);
         }

      });

      new LivroBusiness(repositoryMock).delete(this.LIVRO_COM_ID);

      this.context.assertIsSatisfied();
   }
}
