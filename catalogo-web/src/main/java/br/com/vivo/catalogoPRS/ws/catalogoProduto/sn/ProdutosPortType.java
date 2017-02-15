/**
 * ProdutosPortType.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.vivo.catalogoPRS.ws.catalogoProduto.sn;

public interface ProdutosPortType extends java.rmi.Remote {
    public java.lang.Object atualizaCorProdutos(br.com.vivo.catalogoPRS.ws.catalogoProduto.sn.AtualizaCorProdutosRequest atualizaCorProdutosRequest, String username, String password) throws java.rmi.RemoteException, br.com.vivo.catalogoPRS.ws.catalogoProduto.mc.geral.ErroInfo;
    public br.com.vivo.catalogoPRS.ws.catalogoProduto.sn.BuscarListaCoresPorModeloResponse buscarListaCoresPorModelo(br.com.vivo.catalogoPRS.ws.catalogoProduto.sn.BuscarListaCoresPorModeloRequest buscarListaCoresPorModeloRequest, String username, String password) throws java.rmi.RemoteException, br.com.vivo.catalogoPRS.ws.catalogoProduto.mc.geral.ErroInfo;
    public br.com.vivo.catalogoPRS.ws.catalogoProduto.sn.BuscarListaProdutoPorModeloResponse buscarListaProdutoPorModelo(br.com.vivo.catalogoPRS.ws.catalogoProduto.sn.BuscarListaProdutoPorModeloRequest buscarListaProdutoPorModeloRequest, String username, String password) throws java.rmi.RemoteException, br.com.vivo.catalogoPRS.ws.catalogoProduto.mc.geral.ErroInfo;
    public br.com.vivo.catalogoPRS.ws.catalogoProduto.sn.BuscarListaProdutoResponse buscarListaProduto(br.com.vivo.catalogoPRS.ws.catalogoProduto.sn.BuscarListaProdutoRequest buscarListaProdutoRequest, String username, String password) throws java.rmi.RemoteException, br.com.vivo.catalogoPRS.ws.catalogoProduto.mc.geral.ErroInfo;
    public br.com.vivo.catalogoPRS.ws.catalogoProduto.sn.AssociarProdModeloHabilitarVariaveisResponse associarProdModeloHabilitarVariaveis(br.com.vivo.catalogoPRS.ws.catalogoProduto.sn.AssociarProdModeloHabilitarVariaveisRequest associarProdModeloHabilitarVariaveisRequest, String username, String password) throws java.rmi.RemoteException, br.com.vivo.catalogoPRS.ws.catalogoProduto.mc.geral.ErroInfo;
    public br.com.vivo.catalogoPRS.ws.catalogoProduto.sn.BuscarListaProdutoSemCodigoAssociadoResponse buscarListaProdutoSemCodigoAssociado(br.com.vivo.catalogoPRS.ws.catalogoProduto.sn.BuscarListaProdutoSemCodigoAssociadoRequest buscarListaProdutoSemCodigoAssociadoRequest, String username, String password) throws java.rmi.RemoteException, br.com.vivo.catalogoPRS.ws.catalogoProduto.mc.geral.ErroInfo;
    public java.lang.Object alterarDescricaoCatalogo(br.com.vivo.catalogoPRS.ws.catalogoProduto.sn.AlterarDescricaoCatalogoRequest alterarDescricaoCatalogoRequest, String username, String password) throws java.rmi.RemoteException, br.com.vivo.catalogoPRS.ws.catalogoProduto.mc.geral.ErroInfo;
    public br.com.vivo.catalogoPRS.ws.catalogoProduto.sn.DisponibilizarProdutoResponse disponibilizarProduto(br.com.vivo.catalogoPRS.ws.catalogoProduto.sn.DisponibilizarProdutoRequest disponibilizarProdutoRequest, String username, String password) throws java.rmi.RemoteException, br.com.vivo.catalogoPRS.ws.catalogoProduto.mc.geral.ErroInfo;
    public br.com.vivo.catalogoPRS.ws.catalogoProduto.sn.DesassociarProdModDesabilitarVariaveisResponse desassociarProdModDesabilitarVariaveis(br.com.vivo.catalogoPRS.ws.catalogoProduto.sn.DesassociarProdModDesabilitarVariaveisRequest desassociarProdModDesabilitarVariaveisRequest, String username, String password) throws java.rmi.RemoteException, br.com.vivo.catalogoPRS.ws.catalogoProduto.mc.geral.ErroInfo;
    public br.com.vivo.catalogoPRS.ws.catalogoProduto.sn.BuscarQuantidadeModeloPorProdutoResponse buscarQuantidadeModeloPorProduto(br.com.vivo.catalogoPRS.ws.catalogoProduto.sn.BuscarQuantidadeModeloPorProdutoRequest buscarQuantidadeModeloPorProdutoRequest, String username, String password) throws java.rmi.RemoteException, br.com.vivo.catalogoPRS.ws.catalogoProduto.mc.geral.ErroInfo;
}
