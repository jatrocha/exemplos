package helper;

import org.hibernate.ejb.Ejb3Configuration;

import br.com.cygnus.exemplos.persistence.model.Carro;
import br.com.cygnus.framework.template.dao.entity.test.AbstractTestDAO;

public abstract class DaoTestHelper extends AbstractTestDAO {

   @Override
   protected void registerEntities(Ejb3Configuration configuration) {

      configuration.addAnnotatedClass(Carro.class);
   }

}
