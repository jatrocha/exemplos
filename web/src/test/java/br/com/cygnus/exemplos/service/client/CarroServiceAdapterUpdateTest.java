package br.com.cygnus.exemplos.service.client;

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

import br.com.cygnus.exemplos.JerseyTestBuilder;
import br.com.cygnus.exemplos.business.impl.CarroBusiness;
import br.com.cygnus.exemplos.commons.dto.CarroDTO;
import br.com.cygnus.exemplos.commons.enums.Marca;
import br.com.cygnus.exemplos.resources.CarroResource;
import br.com.cygnus.framework.exception.EngineRuntimeException;

import com.sun.jersey.test.framework.JerseyTest;

public class CarroServiceAdapterUpdateTest {

   private static final Long ID = Long.valueOf(1);

   private static final CarroDTO CARRO_PARA_ATUALIZACAO = CarroDTO.buildWith(ID, Marca.FIAT.name(), "modelo", "versao", "motor");

   private final CarroResource resource = new CarroResource();

   private CarroServiceAdapter adapter;

   private final JerseyTest server = JerseyTestBuilder.createJerseyTestBuilder().addResource(this.resource).build();

   private CarroDTO actual;

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
   public void testUpdateQuandoErroGeral() {

      final CarroBusiness businessMock = this.context.mock(CarroBusiness.class);

      this.context.checking(new Expectations() {

         {

            this.one(businessMock).update(this.with(any(CarroDTO.class)));

            this.will(throwException(new EngineRuntimeException(MENSAGEM_ERRO_PADRAO_PARA_EXCEPTIONS)));
         }

      });

      this.resource.setBusiness(businessMock);

      try {

         this.adapter.update(CARRO_PARA_ATUALIZACAO);

         fail(EXCEPTION_DEVERIA_TER_SIDO_LANCADA);

      } catch (EngineRuntimeException e) {

         assertEquals(MENSAGEM_ERRO_PADRAO_PARA_EXCEPTIONS, e.getErrors().iterator().next().getDescription());
      }

      this.context.assertIsSatisfied();
   }

   @Test
   public void testUpdate() {

      this.resource.setBusiness(new CarroBusiness() {

         @Override
         public void update(CarroDTO dto) {

            CarroServiceAdapterUpdateTest.this.actual = dto;
         }

      });

      this.adapter.update(CARRO_PARA_ATUALIZACAO);

      assertEquals(CARRO_PARA_ATUALIZACAO.getId(), this.actual.getId());

      assertEquals(CARRO_PARA_ATUALIZACAO.getMarca(), this.actual.getMarca());

      assertEquals(CARRO_PARA_ATUALIZACAO.getModelo(), this.actual.getModelo());

      assertEquals(CARRO_PARA_ATUALIZACAO.getVersao(), this.actual.getVersao());

      assertEquals(CARRO_PARA_ATUALIZACAO.getMotor(), this.actual.getMotor());
   }

}
