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
import br.com.cygnus.exemplos.business.CarroBusinessStub;
import br.com.cygnus.exemplos.business.impl.CarroBusiness;
import br.com.cygnus.exemplos.commons.dto.CarroDTO;
import br.com.cygnus.exemplos.commons.dto.LivroDTO;
import br.com.cygnus.exemplos.commons.enums.Marca;
import br.com.cygnus.exemplos.resources.CarroResource;
import br.com.cygnus.framework.IObjetoGenerico;
import br.com.cygnus.framework.exception.EngineRuntimeException;

import com.sun.jersey.test.framework.JerseyTest;

public class CarroServiceAdapterFindAllTest {

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
   }

   @Test
   public void testListQuandoErroGeral() {

      final CarroBusiness business = this.context.mock(CarroBusiness.class);

      this.context.checking(new Expectations() {

         {

            this.one(business).findAll();

            this.will(throwException(new EngineRuntimeException(MENSAGEM_ERRO_PADRAO_PARA_EXCEPTIONS)));
         }

      });

      this.resource.setBusiness(business);

      try {

         this.adapter.findAll();

         fail(EXCEPTION_DEVERIA_TER_SIDO_LANCADA);
      } catch (EngineRuntimeException e) {

         assertEquals(MENSAGEM_ERRO_PADRAO_PARA_EXCEPTIONS, e.getErrors().iterator().next().getDescription());
      }

      this.context.assertIsSatisfied();
   }

   @Test
   public void testListQuandoNaoHouverCarros() {

      final CarroBusiness business = this.context.mock(CarroBusiness.class);

      this.context.checking(new Expectations() {

         {

            this.one(business).findAll();

            this.will(returnValue(new ArrayList<LivroDTO>(IObjetoGenerico.NUMERO_INTEIRO_ZERO)));
         }

      });

      this.resource.setBusiness(business);

      List<CarroDTO> resultado = this.adapter.findAll();

      assertNotNull(resultado);

      assertTrue(resultado.isEmpty());

      this.context.assertIsSatisfied();
   }

   @Test
   public void testList() {

      this.resource.setBusiness(new CarroBusinessStub());

      List<CarroDTO> resultado = this.adapter.findAll();

      assertNotNull(resultado);

      assertEquals(Integer.valueOf(3), Integer.valueOf(resultado.size()));

      CarroDTO carro = resultado.get(1);

      assertEquals(Long.valueOf(2), carro.getId());

      assertEquals(Marca.FIAT.name(), carro.getMarca());

      assertEquals("modelo2", carro.getModelo());

      assertEquals("versao2", carro.getVersao());

      assertEquals("motor2", carro.getMotor());
   }

}
