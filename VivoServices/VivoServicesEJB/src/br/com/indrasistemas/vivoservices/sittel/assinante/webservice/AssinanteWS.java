package br.com.indrasistemas.vivoservices.sittel.assinante.webservice;

import br.com.indrasistemas.vivoservices.sittel.assinante.webservice.to.Assinante;
import br.com.indrasistemas.vivoservices.sittel.assinante.webservice.to.ResultadoConsulta;

public interface AssinanteWS {

    public ResultadoConsulta consultaAssinante(Assinante consultaAssinante);

    public ResultadoConsulta consultaAssinanteTerminal(Assinante consultaAssinanteTerminal);

    public ResultadoConsulta consultaInstalacao(Assinante consultaInstalacao);

}
