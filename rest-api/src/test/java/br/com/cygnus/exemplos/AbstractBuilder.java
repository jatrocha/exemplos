package br.com.cygnus.exemplos;

import br.com.cygnus.framework.template.dao.entity.AbstractEntity;

public abstract class AbstractBuilder<T extends AbstractEntity> {

   protected T product;

   abstract T assembleProduct();

   abstract void initProduct();

   {
      this.initProduct();
   }

   public T build() {

      T product = this.assembleProduct();

      this.initProduct();

      return product;
   }

}
