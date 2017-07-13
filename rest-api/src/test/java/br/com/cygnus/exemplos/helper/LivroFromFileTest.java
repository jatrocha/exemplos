package br.com.cygnus.exemplos.helper;

import static br.com.cygnus.framework.IObjetoGenerico.NULL_STRING;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
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

import br.com.cygnus.exemplos.persistence.model.Livro;
import br.com.cygnus.framework.exception.EngineRuntimeException;

public class LivroFromFileTest {

   private static final String LINE = "83df89af-9824-400e-9eab-dd13408d9e29#Biblia##Religi‹o";

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

      new LivroFromFile(reader).withDelimiter(delimiter).load();
   }

   @Test(expected = IllegalArgumentException.class)
   public void testReadFromFileQuandoDelimitadorInvalidoNull() {

      BufferedReader reader = this.context.mock(BufferedReader.class);

      String delimiter = null;

      new LivroFromFile(reader).withDelimiter(delimiter).load();

      this.context.assertIsSatisfied();
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

      new LivroFromFile(reader).withDelimiter(delimiter).load();

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

      List<Livro> list = new LivroFromFile(reader).withDelimiter(delimiter).load();

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

      List<Livro> list = new LivroFromFile(reader).withDelimiter(delimiter).load();

      assertNotNull(list);

      assertFalse(list.isEmpty());

      this.context.assertIsSatisfied();
   }

   @Test(expected = IllegalArgumentException.class)
   public void testParseLineQuandoLinhaInvalidaVazio() {

      BufferedReader reader = this.context.mock(BufferedReader.class);

      String delimiter = "#";

      new LivroFromFile(reader).withDelimiter(delimiter).parseLine(NULL_STRING);
   }

   @Test
   public void testParseLineQuandoAutorNulo() {

      BufferedReader reader = this.context.mock(BufferedReader.class);

      String delimiter = "#";

      Livro livro = new LivroFromFile(reader).withDelimiter(delimiter).parseLine("83df89af-9824-400e-9eab-dd13408d9e29#Biblia##Religi‹o");

      assertEquals("83df89af-9824-400e-9eab-dd13408d9e29", livro.getId());

      assertEquals("Biblia", livro.getTitulo());

      assertNull(livro.getAutor());

      assertEquals("Religi‹o", livro.getGenero());
   }

   @Test
   public void testParseLine() {

      BufferedReader reader = this.context.mock(BufferedReader.class);

      String delimiter = "#";

      Livro livro = new LivroFromFile(reader).withDelimiter(delimiter).parseLine("83df89af-9824-400e-9eab-dd13408d9e31#David Copperfield#Charles Dickens#Classico");

      assertEquals("83df89af-9824-400e-9eab-dd13408d9e31", livro.getId());

      assertEquals("David Copperfield", livro.getTitulo());

      assertEquals("Charles Dickens", livro.getAutor());

      assertEquals("Classico", livro.getGenero());
   }

}
