package br.com.cygnus.exemplos.integrationtests;

import static org.junit.Assert.assertEquals;

import javax.annotation.Resource;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import br.com.cygnus.exemplos.business.impl.LivroBusiness;
import br.com.cygnus.exemplos.business.impl.LivroBusinessTestBase;
import br.com.cygnus.exemplos.commons.dto.LivroDTO;
import br.com.cygnus.exemplos.helper.InitMongoDB;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class LivroBusinessReadIT extends LivroBusinessTestBase {

   @Resource
   private LivroBusiness business;

   @Resource
   private InitMongoDB initMongo;

   @Before
   public void init() throws Exception {

      this.initMongo.init();
   }

   @Test
   public void testRead() {

      LivroDTO livroDTO = this.business.read(this.LIVRO_FILTER_COM_ID);

      assertEquals(this.LIVRO_PARA_LEITURA.getId(), livroDTO.getId());

      assertEquals(this.LIVRO_PARA_LEITURA.getTitulo(), livroDTO.getTitulo());

      assertEquals(this.LIVRO_PARA_LEITURA.getAutor(), livroDTO.getAutor());

      assertEquals(this.LIVRO_PARA_LEITURA.getGenero(), livroDTO.getGenero());
   }

}
