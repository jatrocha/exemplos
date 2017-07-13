package br.com.cygnus.exemplos.resources;

import java.util.List;

import javax.annotation.Resource;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.springframework.stereotype.Component;

import br.com.cygnus.exemplos.business.impl.LivroBusiness;
import br.com.cygnus.exemplos.commons.dto.LivroDTO;
import br.com.cygnus.exemplos.commons.dto.LivroFilterDTO;

/**
 * Endpoint REST para manipulação de {@link LivroDTO}.
 */
@Component
@Path("/livro")
public class LivroResource {

   @Resource
   private LivroBusiness livroBusiness;

   /**
    * @return Lista de todos os {@link LivroDTO}s cadastrados.
    */
   @GET
   @Path("/query")
   @Produces({ MediaType.APPLICATION_JSON })
   public List<LivroDTO> findAll() {

      return this.livroBusiness.findAll();
   }

   /**
    * @return {@link LivroDTO} a partir do seu identificador.
    */
   @GET
   @Produces({ MediaType.APPLICATION_JSON })
   public LivroDTO read(@QueryParam("id") String id) {

      return this.livroBusiness.read(LivroFilterDTO.buildWith(id));
   }

   /**
    * @param dto {@link LivroDTO} a ser criado.
    */
   @POST
   @Consumes({ MediaType.APPLICATION_JSON })
   public void create(LivroDTO dto) {

      this.livroBusiness.create(dto);
   }

   /**
    * @param dto {@link LivroDTO} a ser atualizado.
    */
   @PUT
   @Consumes({ MediaType.APPLICATION_JSON })
   public void update(LivroDTO dto) {

      this.livroBusiness.update(dto);
   }

   /**
    * @param id {@link String} identificando o registro a ser excluido.
    */
   @DELETE
   @Path("{id}")
   public void delete(@PathParam("id") String id) {

      this.livroBusiness.delete(LivroDTO.buildWith(id));
   }

   /**
    * @param livroBusiness stub {@link LivroBusiness} para testes unitarios.
    */
   public void setBusiness(LivroBusiness livroBusiness) {

      this.livroBusiness = livroBusiness;
   }

}
