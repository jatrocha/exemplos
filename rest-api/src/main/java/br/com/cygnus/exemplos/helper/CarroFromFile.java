package br.com.cygnus.exemplos.helper;

import static br.com.cygnus.framework.IObjetoGenerico.NUMERO_INTEIRO_ZERO;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import br.com.cygnus.exemplos.commons.enums.Marca;
import br.com.cygnus.exemplos.persistence.model.Carro;
import br.com.cygnus.framework.exception.EngineRuntimeException;
import br.com.cygnus.framework.template.business.util.LoadFromFile;

/**
 * Utilitario que carrega de um arquivo texto, os dados de um {@link Carro}.
 */
public class CarroFromFile implements LoadFromFile<Carro> {

   private static final int MOTOR_POSITION = 3;

   private static final int VERSAO_POSITION = 2;

   private static final int MODELO_POSITION = 1;

   private static final int MARCA_POSITION = 0;

   private final BufferedReader reader;

   private String delimiter;

   /**
    * @param reader {@link java.io.BufferedReader} carregado as linhas do arquivo contendo os dados do {@link Carro}.
    * @throws java.lang.IllegalArgumentException caso <code>reader</code> seja <code>null</code>.
    */
   public CarroFromFile(BufferedReader reader) {

      if (reader == null) {

         throw new IllegalArgumentException();
      }

      this.reader = reader;

   }

   /**
    * @see br.com.cygnus.exemplos.helper.LoadFromFile#withDelimiter(java.lang.String)
    */
   @Override
   public LoadFromFile<Carro> withDelimiter(String delimiter) {

      if (StringUtils.isEmpty(delimiter)) {

         throw new IllegalArgumentException();
      }

      this.delimiter = delimiter;

      return this;
   }

   /**
    * @see br.com.cygnus.exemplos.helper.LoadFromFile#load()
    */
   @Override
   public List<Carro> load() {

      List<Carro> list = new ArrayList<Carro>(NUMERO_INTEIRO_ZERO);

      try {

         while (this.reader.ready()) {

            String line = this.reader.readLine();

            list.add(this.parseLine(line));
         }
      } catch (IOException e) {

         throw new EngineRuntimeException(e);
      }

      return list;
   }

   /**
    * @see br.com.cygnus.examples.helper.LoadFromFile#parseLine(java.lang.String)
    */
   @Override
   public Carro parseLine(String line) {

      if (StringUtils.isEmpty(line)) {

         throw new IllegalArgumentException();
      }

      String[] array = line.split(this.delimiter);

      return new Carro(null, Marca.valueOf(array[MARCA_POSITION]), array[MODELO_POSITION].trim(), array[VERSAO_POSITION].trim(), array[MOTOR_POSITION].trim());
   }

}
