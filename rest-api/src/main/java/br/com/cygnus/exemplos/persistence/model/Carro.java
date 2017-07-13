package br.com.cygnus.exemplos.persistence.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.data.mongodb.core.mapping.Document;

import br.com.cygnus.exemplos.commons.enums.Marca;
import br.com.cygnus.framework.template.dao.entity.AbstractEntity;

/**
 * Representacao do Carro no banco de dados.
 */
@Document
@Entity
@Table(name = "carro")
public class Carro extends AbstractEntity {

   private static final long serialVersionUID = -3573142284798009093L;

   /** Identificador. */
   @Id
   private Long id;

   private Marca marca;

   private String modelo;

   private String versao;

   private String motor;

   /**
    * Construtor padrao.
    */
   public Carro() {

      super();
   }

   /**
    * @param id {@link Long} identificador.
    * @param marca {@link Marca} marca do carro.
    * @param modelo {@link String} modelo.
    * @param versao {@link String} versao.
    * @param motor {@link String} motor.
    */
   public Carro(Long id, Marca marca, String modelo, String versao, String motor) {

      this();

      this.id = id;

      this.marca = marca;

      this.modelo = modelo;

      this.versao = versao;

      this.motor = motor;
   }

   /**
    * @return {@link Long} identificador.
    */
   @Id
   @GeneratedValue(strategy = GenerationType.SEQUENCE)
   @Column(name = "carro_id", unique = true, nullable = false)
   public Long getId() {

      return this.id;
   }

   /**
    * @param id {@link Long} identificador.
    */
   public void setId(Long id) {

      this.id = id;
   }

   /**
    * @return {@link Marca} marca do carro.
    */
   @Column(name = "marca", nullable = false)
   public Marca getMarca() {

      return this.marca;
   }

   /**
    * @param marca {@link Marca} marca do carro.
    */
   public void setMarca(Marca marca) {

      this.marca = marca;
   }

   /**
    * @return {@link String} modelo do ve’culo.
    */
   @Column(name = "modelo", nullable = false, length = 30)
   public String getModelo() {

      return this.modelo;
   }

   /**
    * @param modelo {@link String} modelo do ve’culo.
    */
   public void setModelo(String modelo) {

      this.modelo = modelo;
   }

   /**
    * @return {@link String} versao.
    */
   @Column(name = "versao", nullable = false, length = 50)
   public String getVersao() {

      return this.versao;
   }

   /**
    * @param versao {@link String} versao.
    */
   public void setVersao(String versao) {

      this.versao = versao;
   }

   /**
    * @return {@link String} motorizacao.
    */
   public String getMotor() {

      return this.motor;
   }

   /**
    * @param motor {@link String} motorizacao.
    */
   public void setMotor(String motor) {

      this.motor = motor;
   }

}
