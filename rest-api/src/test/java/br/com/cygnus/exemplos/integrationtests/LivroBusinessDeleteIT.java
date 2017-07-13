package br.com.cygnus.exemplos.integrationtests;

import static org.junit.Assert.assertTrue;

import javax.annotation.Resource;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import br.com.cygnus.exemplos.business.impl.LivroBusiness;
import br.com.cygnus.exemplos.business.impl.LivroBusinessTestBase;
import br.com.cygnus.exemplos.helper.InitMongoDB;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class LivroBusinessDeleteIT extends LivroBusinessTestBase {

   @Resource
   private LivroBusiness business;

   @Resource
   private InitMongoDB initMongo;

   @Before
   public void init() throws Exception {

      this.initMongo.init();
   }

   @Test
   public void testDelete() {

      this.business.delete(this.LIVRO_COM_ID);

      assertTrue(Boolean.TRUE);
   }
}
