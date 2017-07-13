package br.com.cygnus.exemplos.business.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.cygnus.exemplos.commons.dto.CarroDTO;
import br.com.cygnus.exemplos.commons.dto.CarroFilterDTO;
import br.com.cygnus.exemplos.commons.enums.Marca;
import br.com.cygnus.exemplos.datastore.CarroDataStore;
import br.com.cygnus.exemplos.persistence.model.Carro;
import br.com.cygnus.framework.template.business.converter.Converter;
import br.com.cygnus.framework.template.business.crud.DataManipulation;
import br.com.cygnus.framework.template.business.crud.DataQuery;

/**
 * Manipulacao de {@link CarroDTO}s.
 */
@Service
public class CarroBusiness implements DataManipulation<CarroDTO>, DataQuery<CarroFilterDTO, CarroDTO> {

   @Resource
   private CarroDataStore dataStore;

   /**
    * Construtor padrao.
    */
   public CarroBusiness() {

      super();
   }

   /**
    * @param dataStore {@link CarroDataStore} injetado pelos testes unitarios.
    */
   protected CarroBusiness(CarroDataStore dataStore) {

      this();

      this.dataStore = dataStore;
   }

   /**
    * @see br.com.cygnus.exemplos.business.DataQuery#read(br.com.cygnus.framework.template.business.dto.AbstractFilterDTO).
    */
   @Override
   @Transactional(propagation = Propagation.NEVER)
   public CarroDTO read(CarroFilterDTO dto) {

      return new CarroToCarroDTOConverter().convert(this.dataStore.find(Carro.class, dto.getId()));
   }

   /**
    * @see br.com.cygnus.exemplos.business.DataQuery#findAll().
    */
   @Override
   @Transactional(propagation = Propagation.NEVER)
   public List<CarroDTO> findAll() {

      final List<Carro> list = this.dataStore.list();

      return new ListCarroToListCarroDTOConverter().convert(list);
   }

   /**
    * @see br.com.cygnus.exemplos.business.DataQuery#findBy(br.com.cygnus.framework.template.business.dto.AbstractFilterDTO)
    */
   @Override
   @Transactional(propagation = Propagation.NEVER)
   public List<CarroDTO> findBy(CarroFilterDTO dto) {
      // TODO Auto-generated method stub
      return null;
   }

   /**
    * @see br.com.cygnus.exemplos.business.DataManipulation#create(br.com.cygnus.framework.template.business.dto.AbstractDTO).
    */
   @Override
   @Transactional(propagation = Propagation.REQUIRED)
   public void create(CarroDTO dto) {

      this.dataStore.save(new CarroDTOToCarroConverter().convert(dto));
   }

   /**
    * @see br.com.cygnus.exemplos.business.DataManipulation#update(br.com.cygnus.framework.template.business.dto.AbstractDTO).
    */
   @Override
   public void update(CarroDTO dto) {

      this.dataStore.update(new CarroDTOToCarroConverter().convert(dto));
   }

   /**
    * @see br.com.cygnus.exemplos.business.DataManipulation#delete(br.com.cygnus.framework.template.business.dto.AbstractDTO).
    */
   @Override
   @Transactional(propagation = Propagation.REQUIRED)
   public void delete(CarroDTO dto) {

      this.dataStore.delete(this.dataStore.find(Carro.class, dto.getId()));
   }

   private static class CarroToCarroDTOConverter implements Converter<Carro, CarroDTO> {

      /**
       * @see br.com.cygnus.framework.template.business.converter.Converter#convert(java.lang.Object).
       */
      @Override
      public CarroDTO convert(Carro source) {

         return CarroDTO.buildWith(source.getId(), source.getMarca().name(), source.getModelo(), source.getVersao(), source.getMotor());
      }
   }

   private static class CarroDTOToCarroConverter implements Converter<CarroDTO, Carro> {

      /**
       * @see br.com.cygnus.framework.template.business.converter.Converter#convert(java.lang.Object).
       */
      @Override
      public Carro convert(CarroDTO source) {

         return new Carro(source.getId(), Marca.valueOf(source.getMarca()), source.getModelo(), source.getVersao(), source.getMotor());
      }
   }

   private static class ListCarroToListCarroDTOConverter implements Converter<List<Carro>, List<CarroDTO>> {

      /**
       * @see br.com.cygnus.framework.template.business.converter.Converter#convert(java.lang.Object).
       */
      @Override
      public List<CarroDTO> convert(List<Carro> source) {

         List<CarroDTO> retorno = new ArrayList<CarroDTO>();

         for (Carro carro : source) {

            retorno.add(new CarroToCarroDTOConverter().convert(carro));
         }

         return retorno;
      }
   }

}
