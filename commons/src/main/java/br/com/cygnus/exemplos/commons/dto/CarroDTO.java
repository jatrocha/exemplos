package br.com.cygnus.exemplos.commons.dto;

import javax.xml.bind.annotation.XmlRootElement;

import br.com.cygnus.framework.template.business.dto.AbstractDTO;

/**
 * Transferencia de dados do <code>Carro</code>.
 */
@XmlRootElement
public class CarroDTO extends AbstractDTO {

   private static final long serialVersionUID = 5358773726271975802L;

   private Long id;

   private String modelo;

   private String marca;

   private String versao;

   private String motor;

   /**
    * Construtor padrao.
    */
   public CarroDTO() {

      super();
   }

   /**
    * @param id {@link Long} identificador.
    * @param modelo {@link String} modelo.
    * @param marca {@link String} marca.
    * @param versao {@link String} versao.
    * @param motor {@link String} motor.
    */
   protected CarroDTO(Long id, String modelo, String marca, String versao, String motor) {

      this();

      this.id = id;

      this.modelo = modelo;

      this.marca = marca;

      this.versao = versao;

      this.motor = motor;
   }

   /**
    * @param id {@link Long} identificador.
    * @param modelo {@link String} modelo.
    * @param marca {@link String} marca.
    * @param versao {@link String} versao.
    * @param motor {@link String} motor.
    * @return {@link CarroDTO}.
    */
   public static CarroDTO buildWith(Long id, String marca, String modelo, String versao, String motor) {

      return new CarroDTO(id, modelo, marca, versao, motor);
   }

   /**
    * @param id {@link Long} identificador do registro.
    * @return {@link CarroDTO} instanciado apenas com o identificador.
    */
   public static CarroDTO buildWith(Long id) {

      return new CarroDTO(id, null, null, null, null);
   }

   /**
    * @return the id
    */
   public final Long getId() {

      return this.id;
   }

   /**
    * @return the modelo
    */
   public final String getModelo() {
      return this.modelo;
   }

   /**
    * @return the marca
    */
   public final String getMarca() {
      return this.marca;
   }

   /**
    * @return the versao
    */
   public final String getVersao() {
      return this.versao;
   }

   /**
    * @return the motor
    */
   public final String getMotor() {
      return this.motor;
   }

}
