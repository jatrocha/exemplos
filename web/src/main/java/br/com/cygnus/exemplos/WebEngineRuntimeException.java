package br.com.cygnus.exemplos;

import java.util.List;

import br.com.cygnus.framework.dto.ErrorDTO;
import br.com.cygnus.framework.exception.EngineRuntimeException;

public class WebEngineRuntimeException extends EngineRuntimeException {

	private static final long serialVersionUID = 5264412548539043733L;

	public WebEngineRuntimeException() {
		super();
	}

	public WebEngineRuntimeException(List<ErrorDTO> errors) {
		super(errors);
	}

	public WebEngineRuntimeException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

	public WebEngineRuntimeException(String arg0) {
		super(arg0);
	}

	public WebEngineRuntimeException(Throwable arg0) {
		super(arg0);
	}



}
