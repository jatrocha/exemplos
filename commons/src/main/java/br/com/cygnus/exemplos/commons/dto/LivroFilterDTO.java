package br.com.cygnus.exemplos.commons.dto;

import javax.xml.bind.annotation.XmlRootElement;

import br.com.cygnus.framework.template.business.dto.AbstractFilterDTO;

/**
 * Filtro para pesquisas e buscas parametricas para {@link br.com.cygnus.exemplos.commons.dto.LivroDTO}.
 */
@XmlRootElement
public class LivroFilterDTO extends AbstractFilterDTO {

   private static final long serialVersionUID = 289779452196013173L;

   private String id;

   /**
    * Construtor padrao.
    */
   public LivroFilterDTO() {

      super();
   }

   /**
    * @param id id {@link String} identificador do {@link LivroDTO}.
    */
   protected LivroFilterDTO(String id) {

      this();

      this.id = id;
   }

   /**
    * @param id {@link String} identificador do {@link LivroDTO}.
    * @return {@link LivroFilterDTO} configurado.
    */
   public static LivroFilterDTO buildWith(String id) {

      return new LivroFilterDTO(id);
   }

   /**
    * @return {@link String} identificador do {@link LivroDTO}.
    */
   public String getId() {

      return this.id;
   }

}
