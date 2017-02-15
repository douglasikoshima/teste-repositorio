/**
 * ProgramaPontosPortType.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.vivo.catalogoPRS.ws.catalogoProgramaPontos.sn;

public interface ProgramaPontosPortType extends java.rmi.Remote {
    public br.com.vivo.catalogoPRS.ws.catalogoProgramaPontos.sn.BuscarListaPrecoProdutoPPResponse buscarListaPrecoProdutoPP(br.com.vivo.catalogoPRS.ws.catalogoProgramaPontos.sn.BuscarListaPrecoProdutoPPRequest buscarListaPrecoProdutoPPRequest, String username, String password) throws java.rmi.RemoteException, br.com.vivo.catalogoPRS.ws.catalogoProgramaPontos.mc.geral.ErroInfo;
    public br.com.vivo.catalogoPRS.ws.catalogoProgramaPontos.sn.AlterarDispProdutoPPResponse alterarDispProdutoPP(br.com.vivo.catalogoPRS.ws.catalogoProgramaPontos.sn.AlterarDispProdutoPPRequest alterarDispProdutoPPRequest, String username, String password) throws java.rmi.RemoteException, br.com.vivo.catalogoPRS.ws.catalogoProgramaPontos.mc.geral.ErroInfo;
    public br.com.vivo.catalogoPRS.ws.catalogoProgramaPontos.sn.BuscarConfigProdutoPPResponse buscarConfigProdutoPP(br.com.vivo.catalogoPRS.ws.catalogoProgramaPontos.sn.BuscarConfigProdutoPPRequest buscarConfigProdutoPPRequest, String username, String password) throws java.rmi.RemoteException, br.com.vivo.catalogoPRS.ws.catalogoProgramaPontos.mc.geral.ErroInfo;
    public br.com.vivo.catalogoPRS.ws.catalogoProgramaPontos.sn.CarregarDispProdutoPPResponse carregarDispProdutoPP(br.com.vivo.catalogoPRS.ws.catalogoProgramaPontos.sn.CarregarDispProdutoPPRequest carregarDispProdutoPPRequest, String username, String password) throws java.rmi.RemoteException, br.com.vivo.catalogoPRS.ws.catalogoProgramaPontos.mc.geral.ErroInfo;
}
