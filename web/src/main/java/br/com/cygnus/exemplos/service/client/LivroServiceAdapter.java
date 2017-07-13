package br.com.cygnus.exemplos.service.client;

import java.util.List;

import javax.ws.rs.core.MediaType;

import br.com.cygnus.exemplos.commons.dto.LivroDTO;
import br.com.cygnus.exemplos.commons.dto.LivroFilterDTO;
import br.com.cygnus.framework.service.RESTServiceAdapter;

import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.GenericType;

/**
 * Adaptador de servico <code>REST</code> para consulta de {@link LivroDTO}.
 */
public class LivroServiceAdapter extends AbstractRestServiceAdapter {

   private static final String URI_LIVRO = "/livro";

   private static final String URI_LIVRO_LIST = URI_LIVRO + "/query";

   /**
    * @return lista contendo todos os {@link LivroDTO} cadastrados.
    */
   public List<LivroDTO> findAll() {

      ClientResponse clientResponse = this.getWebResourceAbsolutePathRESTAPI(URI_LIVRO_LIST).accept(MediaType.APPLICATION_JSON).get(ClientResponse.class);

      GenericType<List<LivroDTO>> genericType = new ListaLivroDTOGenericType();

      return this.getResponseData(clientResponse, genericType);
   }

   /**
    * @param filter {@link LivroFilterDTO}.
    * @return {@link LivroDTO} recuperado a partir do seu identificador, <code>null</code> caso nao seja encontrado.
    */
   public LivroDTO read(LivroFilterDTO filter) {

      ClientResponse clientResponse = this.getWebResourceAbsolutePathRESTAPI(URI_LIVRO).queryParam("id", filter.getId()).accept(MediaType.APPLICATION_JSON)
            .get(ClientResponse.class);

      GenericType<LivroDTO> genericType = new LivroDTOGenericType();

      return this.getResponseData(clientResponse, genericType);
   }

   /**
    * @param dto {@link LivroDTO} a ser inserido.
    */
   public void create(LivroDTO dto) {

      super.getResponseData(this.getWebResourceAbsolutePathRESTAPI(URI_LIVRO).type(MediaType.APPLICATION_JSON).post(ClientResponse.class, dto));
   }

   /**
    * @param dto {@link LivroDTO} a ser atualizado.
    */
   public void update(LivroDTO dto) {

      super.getResponseData(this.getWebResourceAbsolutePathRESTAPI(URI_LIVRO).type(MediaType.APPLICATION_JSON).put(ClientResponse.class, dto));
   }

   /**
    * @param filter {@link LivroFilterDTO} contendo os dados do {@link LivroDTO} a ser excluido.
    */
   public void delete(LivroFilterDTO filter) {

      String path = URI_LIVRO.concat("/".concat(filter.getId()));

      super.getResponseData(this.getWebResourceAbsolutePathRESTAPI(path).delete(ClientResponse.class));
   }

   private static class ListaLivroDTOGenericType extends GenericType<List<LivroDTO>> {
   }

   private static class LivroDTOGenericType extends GenericType<LivroDTO> {
   }
}
