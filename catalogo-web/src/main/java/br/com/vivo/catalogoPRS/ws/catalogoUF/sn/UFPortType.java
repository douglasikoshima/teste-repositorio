/**
 * UFPortType.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.vivo.catalogoPRS.ws.catalogoUF.sn;

public interface UFPortType extends java.rmi.Remote {
    public br.com.vivo.catalogoPRS.ws.catalogoUF.sn.BuscarListaUFPorIdPlanoResponse buscarListaUFPorIdPlano(br.com.vivo.catalogoPRS.ws.catalogoUF.sn.BuscarListaUFPorIdPlanoRequest buscarListaUFPorIdPlanoRequest, String username, String password) throws java.rmi.RemoteException, br.com.vivo.catalogoPRS.ws.catalogoUF.mc.geral.ErroInfo;
    public br.com.vivo.catalogoPRS.ws.catalogoUF.sn.BuscarListaUFComDDDPorIdPlanoResponse buscarListaUFComDDDPorIdPlano(br.com.vivo.catalogoPRS.ws.catalogoUF.sn.BuscarListaUFComDDDPorIdPlanoRequest buscarListaUFComDDDPorIdPlanoRequest, String username, String password) throws java.rmi.RemoteException, br.com.vivo.catalogoPRS.ws.catalogoUF.mc.geral.ErroInfo;
    public br.com.vivo.catalogoPRS.ws.catalogoUF.sn.BuscarListaUFResponse buscarListaUF(br.com.vivo.catalogoPRS.ws.catalogoUF.sn.BuscarListaUFRequest buscarListaUFRequest, String username, String password) throws java.rmi.RemoteException, br.com.vivo.catalogoPRS.ws.catalogoUF.mc.geral.ErroInfo;
    public br.com.vivo.catalogoPRS.ws.catalogoUF.sn.BuscarListaUFComDDDResponse buscarListaUFComDDD(br.com.vivo.catalogoPRS.ws.catalogoUF.sn.BuscarListaUFComDDDRequest buscarListaUFComDDDRequest, String username, String password) throws java.rmi.RemoteException, br.com.vivo.catalogoPRS.ws.catalogoUF.mc.geral.ErroInfo;
}
