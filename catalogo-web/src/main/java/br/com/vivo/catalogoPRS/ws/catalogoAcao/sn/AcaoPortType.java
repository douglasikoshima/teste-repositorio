/**
 * AcaoPortType.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.vivo.catalogoPRS.ws.catalogoAcao.sn;

public interface AcaoPortType extends java.rmi.Remote {
    public br.com.vivo.catalogoPRS.ws.catalogoAcao.sn.BuscarListaAcaoResponse buscarListaAcao(br.com.vivo.catalogoPRS.ws.catalogoAcao.sn.BuscarListaAcaoRequest buscarListaAcaoRequest, String username, String password) throws java.rmi.RemoteException, br.com.vivo.catalogoPRS.ws.catalogoAcao.mc.geral.ErroInfo;
}
