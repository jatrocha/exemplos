package br.com.cygnus.exemplos;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import br.com.cygnus.exemplos.commons.enums.Marca;
import br.com.cygnus.exemplos.persistence.model.Carro;

public class CarroBuilderTest {

   @Test
   public void testBuild() {

      Long id = Long.valueOf(1);

      String modelo = "modelo";

      String versao = "1.0";

      String motor = "motor";

      Marca marca = Marca.FIAT;

      Carro carro = new CarroBuilder().withId(id).withMarca(marca).withModelo(modelo).withVersao(versao).withMotor(motor).build();

      assertEquals(id, carro.getId());

      assertEquals(modelo, carro.getModelo());

      assertEquals(versao, carro.getVersao());

      assertEquals(motor, carro.getMotor());

      assertEquals(marca, carro.getMarca());
   }
}
