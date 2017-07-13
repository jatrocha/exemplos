package br.com.cygnus.exemplos;

import br.com.cygnus.exemplos.commons.enums.Marca;
import br.com.cygnus.exemplos.persistence.model.Carro;

public class CarroBuilder extends AbstractBuilder<Carro> {

   private Carro product;

   @Override
   Carro assembleProduct() {

      return this.product;
   }

   @Override
   void initProduct() {

      this.product = new Carro();
   }

   public CarroBuilder withModelo(String modelo) {

      this.product.setModelo(modelo);

      return this;
   }

   public CarroBuilder withId(Long id) {

      this.product.setId(id);

      return this;
   }

   public CarroBuilder withVersao(String versao) {

      this.product.setVersao(versao);

      return this;
   }

   public CarroBuilder withMotor(String motor) {

      this.product.setMotor(motor);

      return this;
   }

   public CarroBuilder withMarca(Marca marca) {

      this.product.setMarca(marca);

      return this;
   }

}
