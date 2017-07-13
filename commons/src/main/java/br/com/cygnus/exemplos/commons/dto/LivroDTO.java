package br.com.cygnus.exemplos.commons.dto;

import javax.xml.bind.annotation.XmlRootElement;

import br.com.cygnus.framework.template.business.dto.AbstractDTO;

/**
 * Transferencia de dados do <code>Livro</code>.
 */
@XmlRootElement
public final class LivroDTO extends AbstractDTO {

   private static final long serialVersionUID = -1885041257493266028L;

   private String id;

   private String titulo;

   private String autor;

   private String genero;

   /**
    * Construtor padrao.
    */
   public LivroDTO() {

      super();
   }

   /**
    * @param id {@link String} identificador.
    * @param titulo {@link String} titulo do livro.
    * @param autor {@link String} nome do autor.
    * @param genero {@link String} genero do livro.
    */
   private LivroDTO(final String id, final String titulo, final String autor, final String genero) {

      this();

      this.id = id;

      this.titulo = titulo;

      this.autor = autor;

      this.genero = genero;
   }

   /**
    * @param id {@link String} identificador.
    * @param titulo {@link String} titulo do livro.
    * @param autor {@link String} nome do autor.
    * @param genero {@link String} genero do livro.
    * @return {@link LivroDTO} instanciado.
    */
   public static LivroDTO buildWith(final String id, final String titulo, final String autor, final String genero) {

      return new LivroDTO(id, titulo, autor, genero);
   }

   /**
    * @param titulo {@link String} titulo do livro.
    * @param autor {@link String} nome do autor.
    * @param genero {@link String} genero do livro.
    * @return {@link LivroDTO} instanciado.
    */
   public static LivroDTO buildWith(final String titulo, final String autor, final String genero) {

      return new LivroDTO(null, titulo, autor, genero);
   }

   /**
    * @param id {@link String} identificador.
    * @return {@link LivroDTO} instanciado.
    */
   public static LivroDTO buildWith(String id) {

      return new LivroDTO(id, null, null, null);
   }

   /**
    * @return {@link String} identificador.
    */
   public String getId() {

      return this.id;
   }

   /**
    * @param id {@link String} identificador.
    */
   public void setId(String id) {

      this.id = id;
   }

   /**
    * @return {@link String} titulo do livro.
    */
   public String getTitulo() {

      return this.titulo;
   }

   /**
    * @param titulo {@link String} titulo do livro..
    */
   public void setTitulo(String titulo) {

      this.titulo = titulo;
   }

   /**
    * @return {@link String} nome do autor.
    */
   public String getAutor() {

      return this.autor;
   }

   /**
    * @param autor {@link String} nome do autor.
    */
   public void setAutor(String autor) {

      this.autor = autor;
   }

   /**
    * @return {@link String} genero do livro.
    */
   public String getGenero() {

      return this.genero;
   }

   /**
    * @param genero {@link String} genero do livro.
    */
   public void setGenero(String genero) {

      this.genero = genero;
   }

}
