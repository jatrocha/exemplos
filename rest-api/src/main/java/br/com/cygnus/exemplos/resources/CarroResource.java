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

import br.com.cygnus.exemplos.business.impl.CarroBusiness;
import br.com.cygnus.exemplos.commons.dto.CarroDTO;
import br.com.cygnus.exemplos.commons.dto.CarroFilterDTO;

/**
 * Endpoint REST para manipulação de {@link CarroDTO}.
 */
@Component
@Path("/carro")
public class CarroResource {

   @Resource(name = "carroBusiness")
   private CarroBusiness business;

   /**
    * @return Lista de todos os {@link CarroDTO}s cadastrados.
    */
   @GET
   @Path("/query")
   @Produces({ MediaType.APPLICATION_JSON })
   public List<CarroDTO> findAll() {

      return this.business.findAll();
   }

   /**
    * @return {@link CarroDTO} a partir do seu identificador.
    */
   @GET
   @Produces({ MediaType.APPLICATION_JSON })
   public CarroDTO read(@QueryParam("id") String id) {

      return this.business.read(CarroFilterDTO.buildWith(Long.valueOf(id)));
   }

   /**
    * @param dto {@link CarroDTO} a ser criado.
    */
   @POST
   @Consumes({ MediaType.APPLICATION_JSON })
   public void create(CarroDTO dto) {

      this.business.create(dto);
   }

   /**
    * @param dto {@link CarroDTO} a ser atualizado.
    */
   @PUT
   @Consumes({ MediaType.APPLICATION_JSON })
   public void update(CarroDTO dto) {

      this.business.update(dto);
   }

   /**
    * @param id {@link String} identificando o registro a ser excluido.
    */
   @DELETE
   @Path("{id}")
   public void delete(@PathParam("id") String id) {

      this.business.delete(CarroDTO.buildWith(Long.valueOf(id)));
   }

   /**
    * @param carroBusiness stub {@link CarroBusiness} para testes unitarios.
    */
   public void setBusiness(CarroBusiness carroBusiness) {

      this.business = carroBusiness;
   }

}
