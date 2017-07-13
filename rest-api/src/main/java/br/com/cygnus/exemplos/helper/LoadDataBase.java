package br.com.cygnus.exemplos.helper;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import br.com.cygnus.exemplos.persistence.model.Carro;
import br.com.cygnus.framework.template.business.util.LoadFromFile;
import br.com.cygnus.framework.util.FileReaderUtil;

/**
 * Carrega.
 */
public class LoadDataBase {

   private EntityManager entityManager = null;

   /**
    * Default constructor.
    */
   public LoadDataBase() {

      super();
   }

   /**
    * @param factory {@link EntityManagerFactory}.
    */
   public LoadDataBase(EntityManagerFactory factory) {

      this();

      this.entityManager = factory.createEntityManager();

      this.load(this.entityManager);
   }

   /**
    * @return {@link EntityManager}.
    */
   protected final EntityManager getEntityManager() {

      return this.entityManager;
   }

   /**
    * Efetua a carga dos versiculos de teste.
    */
   public final void load(EntityManager entityManager) {

      entityManager.getTransaction().begin();

      //TODO passar tambem o nome do arquivo a ser carregado.

      this.loadCarros(entityManager, new CarroFromFile(new FileReaderUtil().read("carros.txt")).withDelimiter("#"));

      entityManager.getTransaction().commit();
   }

   protected final void loadCarros(final EntityManager entityManager, final LoadFromFile<Carro> carrosFromFile) {

      List<Carro> list = carrosFromFile.load();

      for (Carro carro : list) {

         entityManager.persist(carro);
      }
   }

}
