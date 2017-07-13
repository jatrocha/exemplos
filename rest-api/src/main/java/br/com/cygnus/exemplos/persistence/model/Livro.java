package br.com.cygnus.exemplos.persistence.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import br.com.cygnus.framework.template.dao.entity.AbstractEntity;

/**
 * Representacao do Livro no banco de dados.
 */
@Document
public class Livro extends AbstractEntity {

   private static final long serialVersionUID = -7659127646816823748L;

   @Id
   private String id;

   private String titulo;

   private String autor;

   private String genero;

   /**
    * Construtor padrao.
    */
   public Livro() {

      super();
   }

   /**
    * @param id
    * @param autor
    * @param genero
    */
   public Livro(String id, String titulo, String autor, String genero) {

      this();

      this.id = id;

      this.titulo = titulo;

      this.autor = autor;

      this.genero = genero;
   }

   /**
    * @return the id
    */
   public final String getId() {
      return this.id;
   }

   /**
    * @param id the id to set
    */
   public final void setId(String id) {
      this.id = id;
   }

   /**
    * @return the titulo
    */
   public final String getTitulo() {
      return this.titulo;
   }

   /**
    * @param titulo the titulo to set
    */
   public final void setTitulo(String titulo) {
      this.titulo = titulo;
   }

   /**
    * @return the autor
    */
   public final String getAutor() {
      return this.autor;
   }

   /**
    * @param autor the autor to set
    */
   public final void setAutor(String autor) {
      this.autor = autor;
   }

   /**
    * @return the genero
    */
   public final String getGenero() {
      return this.genero;
   }

   /**
    * @param genero the genero to set
    */
   public final void setGenero(String genero) {
      this.genero = genero;
   }

}
