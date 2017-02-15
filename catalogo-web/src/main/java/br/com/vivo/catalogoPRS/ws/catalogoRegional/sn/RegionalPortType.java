/**
 * RegionalPortType.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.vivo.catalogoPRS.ws.catalogoRegional.sn;

public interface RegionalPortType extends java.rmi.Remote {
    public br.com.vivo.catalogoPRS.ws.catalogoRegional.sn.ListaRegionalUf[] buscarListaRegionalComUF(br.com.vivo.catalogoPRS.ws.catalogoRegional.sn.BuscarListaRegionalComUFRequest buscarListaRegionalComUFRequest, String username, String password) throws java.rmi.RemoteException, br.com.vivo.catalogoPRS.ws.catalogoRegional.mc.geral.ErroInfo;
}
