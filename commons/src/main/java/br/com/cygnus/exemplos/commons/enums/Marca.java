package br.com.cygnus.exemplos.commons.enums;

import br.com.cygnus.framework.IObjetoGenerico;

/**
 * Marcas disponiveis para os Carros.
 */
public enum Marca implements IObjetoGenerico {

   FIAT(Integer.valueOf(1)),

   FORD(Integer.valueOf(2)),

   KIA(Integer.valueOf(3)),

   RENAULT(Integer.valueOf(4)),

   CITROEN(Integer.valueOf(5)),

   HONDA(Integer.valueOf(6)),

   HYUNDAI(Integer.valueOf(7)),

   PEUGEOT(Integer.valueOf(8)),

   TOYOTA(Integer.valueOf(9)),

   VOLKSWAGEN(Integer.valueOf(10));

   private final Integer id;

   private Marca(final Integer id) {

      this.id = id;

   }

   /**
    * @return {@link Integer} identificador.
    */
   public final Integer getId() {

      return this.id;
   }

}
