/**
 * OfertaSapPortType.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.vivo.catalogoPRS.ws.catalogoOfertaSap.sn;

public interface OfertaSapPortType extends java.rmi.Remote {
    public br.com.vivo.catalogoPRS.ws.catalogoOfertaSap.sn.VerificarAssociacaoOfertaSapResponse verificarAssociacaoOfertaSap(br.com.vivo.catalogoPRS.ws.catalogoOfertaSap.sn.VerificarAssociacaoOfertaSapRequest verificarAssociacaoOfertaSapRequest, String username, String password) throws java.rmi.RemoteException, br.com.vivo.catalogoPRS.ws.catalogoOfertaSap.mc.geral.ErroInfo;
    public br.com.vivo.catalogoPRS.ws.catalogoOfertaSap.sn.BuscarListaOfertaSapResponse buscarListaOfertaSap(br.com.vivo.catalogoPRS.ws.catalogoOfertaSap.sn.BuscarListaOfertaSapRequest buscarListaOfertaSapRequest, String username, String password) throws java.rmi.RemoteException, br.com.vivo.catalogoPRS.ws.catalogoOfertaSap.mc.geral.ErroInfo;
    public br.com.vivo.catalogoPRS.ws.catalogoOfertaSap.sn.BuscarListaComposicaoResponse buscarListaComposicao(br.com.vivo.catalogoPRS.ws.catalogoOfertaSap.sn.BuscarListaComposicaoRequest buscarListaComposicaoRequest, String username, String password) throws java.rmi.RemoteException, br.com.vivo.catalogoPRS.ws.catalogoOfertaSap.mc.geral.ErroInfo;
    public br.com.vivo.catalogoPRS.ws.catalogoOfertaSap.sn.CriarNovaOfertaSapResponse criarNovaOfertaSap(br.com.vivo.catalogoPRS.ws.catalogoOfertaSap.sn.CriarNovaOfertaSapRequest criarNovaOfertaSapRequest, String username, String password) throws java.rmi.RemoteException, br.com.vivo.catalogoPRS.ws.catalogoOfertaSap.mc.geral.ErroInfo;
    public br.com.vivo.catalogoPRS.ws.catalogoOfertaSap.sn.CriarComposicaoOfertaSapResponse criarComposicaoOfertaSap(br.com.vivo.catalogoPRS.ws.catalogoOfertaSap.sn.CriarComposicaoOfertaSapRequest criarComposicaoOfertaSapRequest, String username, String password) throws java.rmi.RemoteException, br.com.vivo.catalogoPRS.ws.catalogoOfertaSap.mc.geral.ErroInfo;
    public br.com.vivo.catalogoPRS.ws.catalogoOfertaSap.sn.BuscarListaPlanosSemOfertaResponse buscarListaPlanosSemOferta(br.com.vivo.catalogoPRS.ws.catalogoOfertaSap.sn.BuscarListaPlanosSemOfertaRequest buscarListaPlanosSemOfertaRequest, String username, String password) throws java.rmi.RemoteException, br.com.vivo.catalogoPRS.ws.catalogoOfertaSap.mc.geral.ErroInfo;
    public br.com.vivo.catalogoPRS.ws.catalogoOfertaSap.sn.VerificarExistenciaComposicaoResponse verificarExistenciaComposicao(br.com.vivo.catalogoPRS.ws.catalogoOfertaSap.sn.VerificarExistenciaComposicaoRequest verificarExistenciaComposicaoRequest, String username, String password) throws java.rmi.RemoteException, br.com.vivo.catalogoPRS.ws.catalogoOfertaSap.mc.geral.ErroInfo;
    public br.com.vivo.catalogoPRS.ws.catalogoOfertaSap.sn.AlterarOfertaSapResponse alterarOfertaSap(br.com.vivo.catalogoPRS.ws.catalogoOfertaSap.sn.AlterarOfertaSapRequest alterarOfertaSapRequest, String username, String password) throws java.rmi.RemoteException, br.com.vivo.catalogoPRS.ws.catalogoOfertaSap.mc.geral.ErroInfo;
    public br.com.vivo.catalogoPRS.ws.catalogoOfertaSap.sn.DeletarOfertaSapResponse deletarOfertaSap(br.com.vivo.catalogoPRS.ws.catalogoOfertaSap.sn.DeletarOfertaSapRequest deletarOfertaSapRequest, String username, String password) throws java.rmi.RemoteException, br.com.vivo.catalogoPRS.ws.catalogoOfertaSap.mc.geral.ErroInfo;
    public br.com.vivo.catalogoPRS.ws.catalogoOfertaSap.sn.ExcluirComposicaoOfertaResponse excluirComposicaoOferta(br.com.vivo.catalogoPRS.ws.catalogoOfertaSap.sn.ExcluirComposicaoOfertaRequest excluirComposicaoOfertaRequest, String username, String password) throws java.rmi.RemoteException, br.com.vivo.catalogoPRS.ws.catalogoOfertaSap.mc.geral.ErroInfo;
}
