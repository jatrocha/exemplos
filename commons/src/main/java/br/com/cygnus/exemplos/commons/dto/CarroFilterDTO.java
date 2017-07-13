package br.com.cygnus.exemplos.commons.dto;

import javax.xml.bind.annotation.XmlRootElement;

import br.com.cygnus.framework.template.business.dto.AbstractFilterDTO;

/**
 * Filtro para pesquisas e buscas parametricas para {@link br.com.cygnus.exemplos.commons.dto.CarroDTO}.
 */
@XmlRootElement
public final class CarroFilterDTO extends AbstractFilterDTO {

   private static final long serialVersionUID = 650501589552816667L;

   private Long id;

   /**
    * Construtor padrao.
    */
   public CarroFilterDTO() {

      super();
   }

   /**
    * @param id {@link Long} identificador.
    */
   public CarroFilterDTO(Long id) {

      this();

      this.id = id;
   }

   /**
    * @param id {@link Long} identificador.
    * @return {@link CarroFilterDTO}.
    */
   public static CarroFilterDTO buildWith(Long id) {

      return new CarroFilterDTO(id);
   }

   /**
    * @return {@link Long} identificador.
    */
   public Long getId() {

      return this.id;
   }

}
