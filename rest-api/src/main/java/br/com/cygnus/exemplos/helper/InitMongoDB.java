package br.com.cygnus.exemplos.helper;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.data.mongodb.core.MongoTemplate;

import br.com.cygnus.exemplos.persistence.model.Livro;
import br.com.cygnus.framework.util.FileReaderUtil;

/**
 * Service for initializing MongoDB with sample data using {@link MongoTemplate}.
 */
public class InitMongoDB {

   @Resource
   private MongoTemplate mongoTemplate;

   /**
    * Construtor padrao.
    */
   public InitMongoDB() {

      super();
   }

   /**
    * @param mongoTemplate {@link MongoTemplate}.
    */
   public InitMongoDB(MongoTemplate mongoTemplate) {

      this();

      this.mongoTemplate = mongoTemplate;
   }

   /**
    * Initializes and loads data on the database.
    */
   public void init() {

      // Drop existing collections
      this.mongoTemplate.dropCollection("livro");

      List<Livro> livros = new LivroFromFile(new FileReaderUtil().read("livros.txt")).withDelimiter("#").load();

      for (Livro livro : livros) {

         this.mongoTemplate.save(livro);
      }
   }

}
