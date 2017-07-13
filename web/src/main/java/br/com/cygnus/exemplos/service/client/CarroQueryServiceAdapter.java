package br.com.cygnus.exemplos.service.client;

import java.util.List;

import br.com.cygnus.exemplos.commons.dto.CarroDTO;
import br.com.cygnus.framework.service.RESTServiceAdapter;

import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.GenericType;

/**
 * Adaptador de servico <code>REST</code> para consulta de {@link CarroDTO}.
 */
public class CarroQueryServiceAdapter extends AbstractRestServiceAdapter {

   private static final String URI_CARRO_LIST = "/carro/query";

   /**
    * @return lista contendo todos os {@link CarroDTO} cadastrados.
    */
   public List<CarroDTO> list() {

      ClientResponse clientResponse = this.getWebResourceAbsolutePathRESTAPI(URI_CARRO_LIST).put(ClientResponse.class);

      GenericType<List<CarroDTO>> genericType = new GenericType<List<CarroDTO>>(List.class);

      return this.getResponseData(clientResponse, genericType);
   }

   /**
    * @param id {@link Long} id a ser utilizado na consulta.
    * @return {@link CarroDTO}.
    */
   public CarroDTO read(Long id) {

      ClientResponse clientResponse = this.getWebResourceAbsolutePathRESTAPI(URI_CARRO_LIST).queryParam("id", id.toString()).get(ClientResponse.class);

      GenericType<CarroDTO> genericType = new GenericType<CarroDTO>(CarroDTO.class);

      return this.getResponseData(clientResponse, genericType);
   }
}
