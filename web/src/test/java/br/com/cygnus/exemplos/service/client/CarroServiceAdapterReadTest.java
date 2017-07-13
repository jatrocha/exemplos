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
import br.com.cygnus.exemplos.business.CarroBusinessStub;
import br.com.cygnus.exemplos.business.impl.CarroBusiness;
import br.com.cygnus.exemplos.commons.dto.CarroDTO;
import br.com.cygnus.exemplos.commons.dto.CarroFilterDTO;
import br.com.cygnus.exemplos.commons.enums.Marca;
import br.com.cygnus.exemplos.resources.CarroResource;
import br.com.cygnus.framework.exception.EngineRuntimeException;

import com.sun.jersey.test.framework.JerseyTest;

public class CarroServiceAdapterReadTest {

   private static final Long ID = Long.valueOf(1);

   private static final CarroFilterDTO CARRO_FILTER_COM_ID = CarroFilterDTO.buildWith(ID);

   private final CarroResource resource = new CarroResource();

   private CarroServiceAdapter adapter;

   private final JerseyTest server = JerseyTestBuilder.createJerseyTestBuilder().addResource(this.resource).build();

   private Mockery context;

   @Before
   public void init() throws Exception {

      this.adapter = new CarroServiceAdapter();

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

      final CarroBusiness businessMock = this.context.mock(CarroBusiness.class);

      this.context.checking(new Expectations() {

         {

            this.one(businessMock).read(this.with(any(CarroFilterDTO.class)));

            this.will(throwException(new EngineRuntimeException(MENSAGEM_ERRO_PADRAO_PARA_EXCEPTIONS)));
         }

      });

      this.resource.setBusiness(businessMock);

      try {

         this.adapter.read(CARRO_FILTER_COM_ID);

         fail(EXCEPTION_DEVERIA_TER_SIDO_LANCADA);

      } catch (EngineRuntimeException e) {

         assertEquals(MENSAGEM_ERRO_PADRAO_PARA_EXCEPTIONS, e.getErrors().iterator().next().getDescription());
      }

      this.context.assertIsSatisfied();
   }

   @Test
   public void testReadQuandoLivroInexistente() {

      final CarroBusiness businessMock = this.context.mock(CarroBusiness.class);

      this.context.checking(new Expectations() {

         {

            this.one(businessMock).read(this.with(any(CarroFilterDTO.class)));

            this.will(returnValue(new CarroDTO()));
         }

      });

      this.resource.setBusiness(businessMock);

      CarroDTO livro = this.adapter.read(CARRO_FILTER_COM_ID);

      assertNotNull(livro);

      assertNull(livro.getId());

      this.context.assertIsSatisfied();
   }

   @Test
   public void testRead() {

      this.resource.setBusiness(new CarroBusinessStub());

      CarroDTO carro = this.adapter.read(CARRO_FILTER_COM_ID);

      assertNotNull(carro);

      assertEquals(Long.valueOf(1), carro.getId());

      assertEquals(Marca.FIAT.name(), carro.getMarca());

      assertEquals("modelo1", carro.getModelo());

      assertEquals("versao1", carro.getVersao());

      assertEquals("motor1", carro.getMotor());
   }

}
