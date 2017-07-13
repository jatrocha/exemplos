package br.com.cygnus.exemplos.service.client;

import java.util.List;

import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.GenericType;

import br.com.cygnus.exemplos.WebEngineRuntimeException;
import br.com.cygnus.framework.dto.ErrorDTO;
import br.com.cygnus.framework.service.RESTServiceAdapter;

public abstract class AbstractRestServiceAdapter<T> extends RESTServiceAdapter {

	@SuppressWarnings("unchecked")
	@Override
	protected WebEngineRuntimeException createRuntimeException(ClientResponse response) {

		return new WebEngineRuntimeException(response.getEntity(new ErrorGenericType()));
	}

	private static class ErrorGenericType extends GenericType<List<ErrorDTO>> {
	}

}


