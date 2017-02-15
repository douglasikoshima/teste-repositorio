/**
 * ContaPosPortType.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.vivo.fo.ctrls.cliente.fatura.optin32.ws.SN.ContaPos;

public interface ContaPosPortType extends java.rmi.Remote {
    public br.com.vivo.fo.ctrls.cliente.fatura.optin32.ws.MC.Conta.Conta consultarContasEmAberto(br.com.vivo.fo.ctrls.cliente.fatura.optin32.ws.SN.ContaPos.ConsultarContasEmAbertoRequest consultarContasEmAbertoRequest) throws java.rmi.RemoteException, br.com.vivo.fo.ctrls.cliente.fatura.optin32.ws.MC.Geral.ErroInfo;
    public br.com.vivo.fo.ctrls.cliente.fatura.optin32.ws.MC.Conta.Conta consultarConta(br.com.vivo.fo.ctrls.cliente.fatura.optin32.ws.SN.ContaPos.ConsultarContaRequest consultarContaRequest) throws java.rmi.RemoteException, br.com.vivo.fo.ctrls.cliente.fatura.optin32.ws.MC.Geral.ErroInfo;
    public java.lang.Object solicitarSegundaViaConta(br.com.vivo.fo.ctrls.cliente.fatura.optin32.ws.SN.ContaPos.ParametrosSolicitarSegundaViaConta solicitarSegundaViaContaRequest) throws java.rmi.RemoteException, br.com.vivo.fo.ctrls.cliente.fatura.optin32.ws.MC.Geral.ErroInfo;
    public br.com.vivo.fo.ctrls.cliente.fatura.optin32.ws.MC.Conta.Conta consultarFaturaOnLine(br.com.vivo.fo.ctrls.cliente.fatura.optin32.ws.SN.ContaPos.ParametrosConsultarFaturaOnLine consultarFaturaOnLineRequest) throws java.rmi.RemoteException, br.com.vivo.fo.ctrls.cliente.fatura.optin32.ws.MC.Geral.ErroInfo;
    public br.com.vivo.fo.ctrls.cliente.fatura.optin32.ws.MC.Conta.Conta manterFaturaOnLine(br.com.vivo.fo.ctrls.cliente.fatura.optin32.ws.SN.ContaPos.ParametrosManterFaturaOnLine manterFaturaOnLineRequest) throws java.rmi.RemoteException, br.com.vivo.fo.ctrls.cliente.fatura.optin32.ws.MC.Geral.ErroInfo;
    
    public void setUserName(String userName)throws java.rmi.RemoteException;
    public void setPassword(String password) throws java.rmi.RemoteException;
}
