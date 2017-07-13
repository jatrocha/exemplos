package helper;

import java.util.ArrayList;
import java.util.List;

import br.com.cygnus.exemplos.commons.dto.CarroDTO;

public class CarroTestHelper {

   public static final CarroDTO FIAT = CarroDTO.buildWith(Long.valueOf(1), "Uno", "FIAT", "Mille Fire Economy 2 Portas", "1.0 - 8V");

   protected CarroTestHelper() {

      super();
   }

   public static List<CarroDTO> listar() {

      List<CarroDTO> lista = new ArrayList<CarroDTO>();

      lista.add(CarroDTO.buildWith(Long.valueOf(1), "Uno", "FIAT", "Mille Fire Economy 2 Portas", "1.0 - 8V"));

      lista.add(CarroDTO.buildWith(Long.valueOf(2), "Uno", "FIAT", "Mille Fire Economy 4 Portas", "1.0 - 8V"));

      lista.add(CarroDTO.buildWith(Long.valueOf(3), "Uno", "FIAT", "Mille Way Economy 2 Portas", "1.0 - 8V"));

      lista.add(CarroDTO.buildWith(Long.valueOf(4), "Uno", "FIAT", "Mille Way Economy 4 Portas", "1.0 - 8V"));

      return lista;
   }

}
