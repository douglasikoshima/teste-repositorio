/**
 * TipoProdutoPortType.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.vivo.catalogoPRS.ws.catalogoTipoProduto.sn;

public interface TipoProdutoPortType extends java.rmi.Remote {
    public br.com.vivo.catalogoPRS.ws.catalogoTipoProduto.sn.BuscarListaTipoProdutoResponse buscarListaTipoProduto(br.com.vivo.catalogoPRS.ws.catalogoTipoProduto.sn.BuscarListaTipoProdutoRequest buscarListaTipoProdutoRequest, String username, String password) throws java.rmi.RemoteException, br.com.vivo.catalogoPRS.ws.catalogoTipoProduto.mc.geral.ErroInfo;
    public br.com.vivo.catalogoPRS.ws.catalogoTipoProduto.sn.BuscarListaTipoProdutoPorFabricanteResponse buscarListaTipoProdutoPorFabricante(br.com.vivo.catalogoPRS.ws.catalogoTipoProduto.sn.BuscarListaTipoProdutoPorFabricanteRequest buscarListaTipoProdutoPorFabricanteRequest, String username, String password) throws java.rmi.RemoteException, br.com.vivo.catalogoPRS.ws.catalogoTipoProduto.mc.geral.ErroInfo;
}
