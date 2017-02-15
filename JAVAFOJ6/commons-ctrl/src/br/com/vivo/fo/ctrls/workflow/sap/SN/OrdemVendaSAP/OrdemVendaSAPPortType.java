/**
 * OrdemVendaSAPPortType.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.vivo.fo.ctrls.workflow.sap.SN.OrdemVendaSAP;

public interface OrdemVendaSAPPortType extends java.rmi.Remote {
    public java.lang.Object atualizarStatusOrdemVenda(br.com.vivo.fo.ctrls.workflow.sap.SN.OrdemVendaSAP.ParametrosAtualizarStatusOrdemVenda atualizarStatusOrdemVendaRequest) throws java.rmi.RemoteException, br.com.vivo.fo.ctrls.workflow.sap.MC.Geral.ErroInfo;
    public java.lang.Object atualizarCadastroCliente(br.com.vivo.fo.ctrls.workflow.sap.SN.OrdemVendaSAP.ParametrosAtualizarCadastroCliente atualizarCadastroClienteRequest) throws java.rmi.RemoteException, br.com.vivo.fo.ctrls.workflow.sap.MC.Geral.ErroInfo;
    public br.com.vivo.fo.ctrls.workflow.sap.MC.OrdemVenda.NotaFiscal[] consultarNotaFiscal(br.com.vivo.fo.ctrls.workflow.sap.SN.OrdemVendaSAP.ParametrosConsultarNotaFiscal consultarNotaFiscalRequest) throws java.rmi.RemoteException, br.com.vivo.fo.ctrls.workflow.sap.MC.Geral.ErroInfo;
    public br.com.vivo.fo.ctrls.workflow.sap.MC.OrdemVenda.OrdemVenda criarOrdemClienteSAP(br.com.vivo.fo.ctrls.workflow.sap.SN.OrdemVendaSAP.ParametrosCriarOrdemClienteSAP criarOrdemClienteSAPRequest) throws java.rmi.RemoteException, br.com.vivo.fo.ctrls.workflow.sap.MC.Geral.ErroInfo;
}
