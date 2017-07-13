package br.com.cygnus.exemplos.business;

import java.util.ArrayList;
import java.util.List;

import br.com.cygnus.exemplos.business.impl.LivroBusiness;
import br.com.cygnus.exemplos.commons.dto.LivroDTO;
import br.com.cygnus.exemplos.commons.dto.LivroFilterDTO;

public class LivroBusinessStub extends LivroBusiness {

   @Override
   public LivroDTO read(LivroFilterDTO dto) {

      return this.findAll().iterator().next();
   }

   @Override
   public List<LivroDTO> findAll() {

      List<LivroDTO> lista = new ArrayList<LivroDTO>();

      lista.add(LivroDTO.buildWith("id1", "titulo1", "autor1", "genero1"));

      lista.add(LivroDTO.buildWith("id2", "titulo2", "autor2", "genero2"));

      lista.add(LivroDTO.buildWith("id3", "titulo3", "autor3", "genero3"));

      return lista;
   }

   @Override
   public List<LivroDTO> findBy(LivroFilterDTO dto) {

      return null;
   }

}
