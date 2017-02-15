/**
 * DescontoPortType.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.vivo.catalogoPRS.ws.catalogoDesconto.sn;

public interface DescontoPortType extends java.rmi.Remote {
    public br.com.vivo.catalogoPRS.ws.catalogoDesconto.sn.BuscarListaParamDescontoResponse buscarListaParamDesconto(br.com.vivo.catalogoPRS.ws.catalogoDesconto.sn.BuscarListaParamDescontoRequest parameters, String username, String password) throws java.rmi.RemoteException, br.com.vivo.catalogoPRS.ws.catalogoDesconto.mc.geral.ErroInfo;
    public br.com.vivo.catalogoPRS.ws.catalogoDesconto.sn.AlterarParamDescontoResponse alterarParamDesconto(br.com.vivo.catalogoPRS.ws.catalogoDesconto.sn.AlterarParamDescontoRequest parameters, String username, String password) throws java.rmi.RemoteException, br.com.vivo.catalogoPRS.ws.catalogoDesconto.mc.geral.ErroInfo;
    public br.com.vivo.catalogoPRS.ws.catalogoDesconto.sn.AlterarFormaPagtoCanalParamResponse alterarFormaPagtoCanalParam(br.com.vivo.catalogoPRS.ws.catalogoDesconto.sn.AlterarFormaPagtoCanalParamRequest parameters, String username, String password) throws java.rmi.RemoteException, br.com.vivo.catalogoPRS.ws.catalogoDesconto.mc.geral.ErroInfo;
}
