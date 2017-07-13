package br.com.cygnus.exemplos.business;

import java.util.ArrayList;
import java.util.List;

import br.com.cygnus.exemplos.business.impl.CarroBusiness;
import br.com.cygnus.exemplos.commons.dto.CarroDTO;
import br.com.cygnus.exemplos.commons.dto.CarroFilterDTO;
import br.com.cygnus.exemplos.commons.enums.Marca;

public class CarroBusinessStub extends CarroBusiness {

   /**
    * @see br.com.cygnus.exemplos.business.DataQuery#findAll().
    */
   @Override
   public List<CarroDTO> findAll() {

      List<CarroDTO> list = new ArrayList<CarroDTO>(3);

      list.add(CarroDTO.buildWith(Long.valueOf(1), Marca.FIAT.name(), "modelo1", "versao1", "motor1"));

      list.add(CarroDTO.buildWith(Long.valueOf(2), Marca.FIAT.name(), "modelo2", "versao2", "motor2"));

      list.add(CarroDTO.buildWith(Long.valueOf(3), Marca.FIAT.name(), "modelo3", "versao3", "motor3"));

      return list;
   }

   /**
    * @see br.com.cygnus.exemplos.business.DataQuery#read(br.com.cygnus.framework.template.business.dto.AbstractFilterDTO).
    */
   @Override
   public CarroDTO read(CarroFilterDTO dto) {

      return this.findAll().iterator().next();
   }

}
