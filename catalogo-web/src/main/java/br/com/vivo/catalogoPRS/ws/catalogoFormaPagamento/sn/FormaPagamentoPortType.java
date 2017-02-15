/**
 * FormaPagamentoPortType.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.vivo.catalogoPRS.ws.catalogoFormaPagamento.sn;

public interface FormaPagamentoPortType extends java.rmi.Remote {
    public br.com.vivo.catalogoPRS.ws.catalogoFormaPagamento.sn.BuscarListaFormaCondPagtoResponse buscarListaFormaCondPagto(br.com.vivo.catalogoPRS.ws.catalogoFormaPagamento.sn.BuscarListaFormaCondPagtoRequest buscarListaFormaCondPagtoRequest, String username, String password) throws java.rmi.RemoteException, br.com.vivo.catalogoPRS.ws.catalogoFormaPagamento.mc.geral.ErroInfo;
    public br.com.vivo.catalogoPRS.ws.catalogoFormaPagamento.sn.BuscarListaFormaPagamentoResponse buscarListaFormaPagamento(br.com.vivo.catalogoPRS.ws.catalogoFormaPagamento.sn.BuscarListaFormaPagamentoRequest parameters, String username, String password) throws java.rmi.RemoteException, br.com.vivo.catalogoPRS.ws.catalogoFormaPagamento.mc.geral.ErroInfo;
    public br.com.vivo.catalogoPRS.ws.catalogoFormaPagamento.sn.BuscarListaFormaCondPagtoPorTipoProdutoResponse buscarListaFormaCondPagtoPorTipoProduto(br.com.vivo.catalogoPRS.ws.catalogoFormaPagamento.sn.BuscarListaFormaCondPagtoPorTipoProdutoRequest parameters, String username, String password) throws java.rmi.RemoteException, br.com.vivo.catalogoPRS.ws.catalogoFormaPagamento.mc.geral.ErroInfo;
    public br.com.vivo.catalogoPRS.ws.catalogoFormaPagamento.sn.AlterarFormaCondPagtoResponse alterarFormaCondPagto(br.com.vivo.catalogoPRS.ws.catalogoFormaPagamento.sn.AlterarFormaCondPagtoRequest parameters, String username, String password) throws java.rmi.RemoteException, br.com.vivo.catalogoPRS.ws.catalogoFormaPagamento.mc.geral.ErroInfo;
}
