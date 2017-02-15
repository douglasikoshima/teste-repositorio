package br.com.indrasistemas.vivoservices.historico.titulolinha.webservice;

import br.com.indrasistemas.framework.service.to.RequestInfoTO;
import br.com.indrasistemas.vivoservices.historico.titulolinha.webservice.to.TitularidadeLinhaParametrosWSTO;
import br.com.indrasistemas.vivoservices.historico.titulolinha.webservice.to.TitularidadeLinhaWSTO;

public interface TitularidadeLinhaWS {

	public TitularidadeLinhaWSTO pesquisarTitularidadeLinha(RequestInfoTO requestInfo, TitularidadeLinhaParametrosWSTO entrada);

}
