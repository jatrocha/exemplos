package br.com.cygnus.exemplos.business.impl;

import static org.junit.Assert.assertFalse;
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

import br.com.cygnus.exemplos.commons.dto.CarroDTO;
import br.com.cygnus.exemplos.commons.enums.Marca;
import br.com.cygnus.exemplos.datastore.CarroDataStore;
import br.com.cygnus.exemplos.persistence.model.Carro;

public class CarroQueryBusinessTest {

   private Mockery context;

   @Before
   public void setup() {

      this.context = new Mockery() {

         {

            this.setImposteriser(ClassImposteriser.INSTANCE);
         }

      };
   }

   @After
   public void teardown() {

      this.context = null;
   }

   @Test
   public void testListQuandoListaVazia() {

      final CarroDataStore dataStore = this.context.mock(CarroDataStore.class);

      this.context.checking(new Expectations() {

         {

            this.one(dataStore).list();

            this.will(returnValue(new ArrayList<Carro>()));

         }

      });
      
      CarroBusiness business = new CarroBusiness(dataStore);

      List<CarroDTO> list = business.findAll();

      assertNotNull(list);

      assertTrue(list.isEmpty());
   }

   @Test
   public void testListQuandoListaPreenchida() {

      final CarroDataStore dataStore = this.context.mock(CarroDataStore.class);

      final List<Carro> carros = this.getCarros();

      this.context.checking(new Expectations() {

         {

            this.one(dataStore).list();

            this.will(returnValue(carros));

         }

      });

      CarroBusiness business = new CarroBusiness(dataStore);

      List<CarroDTO> list = business.findAll();

      assertNotNull(list);

      assertFalse(list.isEmpty());
   }

   private List<Carro> getCarros() {

      List<Carro> retorno = new ArrayList<Carro>();

      retorno.add(new Carro(Long.valueOf(1), Marca.FORD, "modelo", "versao", "motor"));

      return retorno;
   }
}
