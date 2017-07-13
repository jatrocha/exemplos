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

import br.com.cygnus.exemplos.commons.dto.LivroDTO;
import br.com.cygnus.exemplos.persistence.repository.LivroRepository;
import br.com.cygnus.framework.exception.EngineRuntimeException;

public class LivroBusinessReadTest extends LivroBusinessTestBase {

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
   public void testReadQuandoParametroInvalidoNull() {

      new LivroBusiness().read(null);
   }

   @Test(expected = IllegalArgumentException.class)
   public void testReadQuandoIdInvalidoNull() {

      new LivroBusiness().read(this.LIVRO_FILTER_VAZIO);
   }

   @Test(expected = IllegalArgumentException.class)
   public void testReadQuandoIdInvalidoVazio() {

      new LivroBusiness().read(this.LIVRO_FILTER_ID_VAZIO);
   }

   @Test
   public void testReadQuandoErroGeral() {

      final LivroRepository repositoryMock = this.context.mock(LivroRepository.class);

      this.context.checking(new Expectations() {

         {

            this.one(repositoryMock).findOne(LivroBusinessReadTest.this.ID);

            this.will(throwException(new EngineRuntimeException(MENSAGEM_ERRO_PADRAO_PARA_EXCEPTIONS)));
         }

      });

      LivroBusiness business = new LivroBusiness(repositoryMock);

      try {

         business.read(this.LIVRO_FILTER_COM_ID);

         fail(EXCEPTION_DEVERIA_TER_SIDO_LANCADA);

      } catch (EngineRuntimeException e) {

         assertEquals(MENSAGEM_ERRO_PADRAO_PARA_EXCEPTIONS, e.getErrors().iterator().next().getDescription());
      }

      this.context.assertIsSatisfied();
   }

   @Test
   public void testRead() {

      final LivroRepository repositoryMock = this.context.mock(LivroRepository.class);

      this.context.checking(new Expectations() {

         {

            this.one(repositoryMock).findOne(LivroBusinessReadTest.this.ID);

            this.will(returnValue(LivroBusinessReadTest.this.LIVRO_PARA_LEITURA));
         }

      });

      LivroDTO livroDTO = new LivroBusiness(repositoryMock).read(this.LIVRO_FILTER_COM_ID);

      assertEquals(this.LIVRO_PARA_LEITURA.getId(), livroDTO.getId());

      assertEquals(this.LIVRO_PARA_LEITURA.getTitulo(), livroDTO.getTitulo());

      assertEquals(this.LIVRO_PARA_LEITURA.getAutor(), livroDTO.getAutor());

      assertEquals(this.LIVRO_PARA_LEITURA.getGenero(), livroDTO.getGenero());

      this.context.assertIsSatisfied();
   }
}
