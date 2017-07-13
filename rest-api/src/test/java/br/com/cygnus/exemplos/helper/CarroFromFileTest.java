package br.com.cygnus.exemplos.helper;

import static br.com.cygnus.framework.IObjetoGenerico.NULL_STRING;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.lib.legacy.ClassImposteriser;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import br.com.cygnus.exemplos.commons.enums.Marca;
import br.com.cygnus.exemplos.persistence.model.Carro;
import br.com.cygnus.framework.exception.EngineRuntimeException;
import br.com.cygnus.framework.template.business.util.LoadFromFile;

public class CarroFromFileTest {

   private static final String LINE = "FIAT# Uno# Mille Fire Economy 2 Portas# 1.0 - 8V";

   private Mockery context;

   @Before
   public void setup() {

      this.context = new Mockery() {

         {

            this.setImposteriser(ClassImposteriser.INSTANCE);
         }

      };
   }

   @After
   public void teardown() {

      this.context = null;
   }

   @Test(expected = IllegalArgumentException.class)
   public void testReadFromFileQuandoReaderInvalidoNull() {

      BufferedReader reader = null;

      String delimiter = "#";

      new CarroFromFile(reader).withDelimiter(delimiter).load();
   }

   @Test(expected = IllegalArgumentException.class)
   public void testReadFromFileQuandoDelimitadorInvalidoNull() {

      BufferedReader reader = this.context.mock(BufferedReader.class);

      String delimiter = null;

      new CarroFromFile(reader).withDelimiter(delimiter).load();

      this.context.assertIsSatisfied();
   }

   @Test(expected = IllegalArgumentException.class)
   public void testReadFromFileQuandoDelimitadorInvalidoVazio() {

      BufferedReader reader = this.context.mock(BufferedReader.class);

      String delimiter = "";

      new CarroFromFile(reader).withDelimiter(delimiter).load();

      this.context.assertIsSatisfied();
   }

   @Test
   public void testReadFromFileQuandoDelimitadorValido() {

      BufferedReader reader = this.context.mock(BufferedReader.class);

      String delimiter = "#";

      LoadFromFile<Carro> carroFromFile = new CarroFromFile(reader).withDelimiter(delimiter);

      assertNotNull(carroFromFile);
   }

   @Test(expected = EngineRuntimeException.class)
   public void testLoadQuandoErroDeIO() throws IOException {

      final BufferedReader reader = this.context.mock(BufferedReader.class);

      this.context.checking(new Expectations() {

         {

            this.one(reader).ready();

            this.will(throwException(new IOException()));
         }

      });

      String delimiter = "#";

      new CarroFromFile(reader).withDelimiter(delimiter).load();

      this.context.assertIsSatisfied();
   }

   @Test
   public void testLoadQuandoArquivoVazio() throws IOException {

      final BufferedReader reader = this.context.mock(BufferedReader.class);

      this.context.checking(new Expectations() {

         {

            this.one(reader).ready();

            this.will(returnValue(Boolean.FALSE));
         }

      });

      String delimiter = "#";

      List<Carro> list = new CarroFromFile(reader).withDelimiter(delimiter).load();

      assertNotNull(list);

      assertTrue(list.isEmpty());

      this.context.assertIsSatisfied();
   }

   @Test
   public void testLoad() throws IOException {

      final BufferedReader reader = this.context.mock(BufferedReader.class);

      this.context.checking(new Expectations() {

         {

            this.one(reader).ready();

            this.will(returnValue(Boolean.TRUE));

            this.one(reader).readLine();

            this.will(returnValue(LINE));

            this.one(reader).ready();

            this.will(returnValue(Boolean.FALSE));
         }

      });

      String delimiter = "#";

      List<Carro> list = new CarroFromFile(reader).withDelimiter(delimiter).load();

      assertNotNull(list);

      assertFalse(list.isEmpty());

      this.context.assertIsSatisfied();
   }

   @Test(expected = IllegalArgumentException.class)
   public void testParseLineQuandoLinhaInvalidaVazio() {

      BufferedReader reader = this.context.mock(BufferedReader.class);

      String delimiter = "#";

      new CarroFromFile(reader).withDelimiter(delimiter).parseLine(NULL_STRING);
   }

   @Test
   public void testParseLine() {

      BufferedReader reader = this.context.mock(BufferedReader.class);

      String delimiter = "#";

      Carro carro = new CarroFromFile(reader).withDelimiter(delimiter).parseLine(LINE);

      assertEquals(Marca.FIAT.name(), carro.getMarca().name());

      assertEquals("Uno", carro.getModelo());

      assertEquals("Mille Fire Economy 2 Portas", carro.getVersao());

      assertEquals("1.0 - 8V", carro.getMotor());
   }

}
