package br.com.cygnus.exemplos.datastore;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Service;

import br.com.cygnus.exemplos.persistence.model.Carro;
import br.com.cygnus.framework.persistence.DataStoreBase;

/**
 * Utilitario para manipular {@link Carro}.
 */
@Service
public class CarroDataStore extends DataStoreBase<Carro> {

   /**
    * @return lista todas as entidades {@link Carro} cadastradas no banco de dados.
    */
   public List<Carro> list() {

      CriteriaBuilder cb = this.getEntityManager().getCriteriaBuilder();

      CriteriaQuery<Carro> q = cb.createQuery(Carro.class);

      Root<Carro> c = q.from(Carro.class);

      return this.getEntityManager().createQuery(q.select(c)).getResultList();
   }

}
