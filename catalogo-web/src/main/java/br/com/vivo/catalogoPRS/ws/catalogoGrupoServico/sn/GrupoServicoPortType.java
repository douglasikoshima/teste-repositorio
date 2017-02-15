/**
 * GrupoServicoPortType.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.vivo.catalogoPRS.ws.catalogoGrupoServico.sn;

public interface GrupoServicoPortType extends java.rmi.Remote {
    public br.com.vivo.catalogoPRS.ws.catalogoGrupoServico.sn.CriarGrupoServicoCatalogoResponse criarGrupoServicoCatalogo(br.com.vivo.catalogoPRS.ws.catalogoGrupoServico.sn.CriarGrupoServicoCatalogoRequest criarGrupoServicoCatalogoRequest, String username, String password) throws java.rmi.RemoteException, br.com.vivo.catalogoPRS.ws.catalogoGrupoServico.mc.geral.ErroInfo;
    public br.com.vivo.catalogoPRS.ws.catalogoGrupoServico.sn.AlterarGrupoServicoCatalogoResponse alterarGrupoServicoCatalogo(br.com.vivo.catalogoPRS.ws.catalogoGrupoServico.sn.AlterarGrupoServicoCatalogoRequest alterarGrupoServicoCatalogoRequest, String username, String password) throws java.rmi.RemoteException, br.com.vivo.catalogoPRS.ws.catalogoGrupoServico.mc.geral.ErroInfo;
    public br.com.vivo.catalogoPRS.ws.catalogoGrupoServico.sn.BuscarListaGrupoServicoResponse buscarListaGrupoServico(br.com.vivo.catalogoPRS.ws.catalogoGrupoServico.sn.BuscarListaGrupoServicoRequest buscarListaGrupoServicoRequest, String username, String password) throws java.rmi.RemoteException, br.com.vivo.catalogoPRS.ws.catalogoGrupoServico.mc.geral.ErroInfo;
    public br.com.vivo.catalogoPRS.ws.catalogoGrupoServico.sn.BuscarServicoPorGrupoServicoCatalogoResponse buscarServicoPorGrupoServicoCatalogo(br.com.vivo.catalogoPRS.ws.catalogoGrupoServico.sn.BuscarServicoPorGrupoServicoCatalogoRequest buscarServicoPorGrupoServicoCatalogoRequest, String username, String password) throws java.rmi.RemoteException, br.com.vivo.catalogoPRS.ws.catalogoGrupoServico.mc.geral.ErroInfo;
    public br.com.vivo.catalogoPRS.ws.catalogoGrupoServico.sn.ExcluirListaGrupoServicoCatalogoResponse excluirListaGrupoServicoCatalogo(br.com.vivo.catalogoPRS.ws.catalogoGrupoServico.sn.ExcluirListaGrupoServicoCatalogoRequest excluirListaGrupoServicoCatalogoRequest, String username, String password) throws java.rmi.RemoteException, br.com.vivo.catalogoPRS.ws.catalogoGrupoServico.mc.geral.ErroInfo;
}
