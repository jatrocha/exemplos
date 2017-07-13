package br.com.cygnus.exemplos.helper;

import static org.junit.Assert.assertTrue;
import helper.DaoTestHelper;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.lib.legacy.ClassImposteriser;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import br.com.cygnus.exemplos.persistence.model.Carro;

public class LoadDataBaseIT extends DaoTestHelper {

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
   public void testLoadCarrosQuandoListaVazia() {

      final EntityManager entityManager = this.context.mock(EntityManager.class);

      final CarroFromFile carroFromFile = this.context.mock(CarroFromFile.class);

      final List<Carro> carros = new ArrayList<Carro>();

      this.context.checking(new Expectations() {

         {

            this.one(carroFromFile).load();

            this.will(returnValue(carros));

            this.never(entityManager).persist(this.with(any(Carro.class)));
         }

      });

      new LoadDataBase().loadCarros(entityManager, carroFromFile);

      this.context.assertIsSatisfied();
   }

   @Test
   public void testLoadCarros() {

      final EntityManager entityManager = this.context.mock(EntityManager.class);

      final CarroFromFile carroFromFile = this.context.mock(CarroFromFile.class);

      final List<Carro> carros = this.listarCarros();

      this.context.checking(new Expectations() {

         {

            this.one(carroFromFile).load();

            this.will(returnValue(carros));

            this.one(entityManager).persist(this.with(any(Carro.class)));
         }

      });

      new LoadDataBase().loadCarros(entityManager, carroFromFile);

      this.context.assertIsSatisfied();
   }

   @Test
   public void testLoad() {

      new LoadDataBase().load(this.getEntityManager());

      assertTrue(Boolean.TRUE);
   }

   private List<Carro> listarCarros() {

      List<Carro> list = new ArrayList<Carro>();

      list.add(new Carro());

      return list;
   }

}
