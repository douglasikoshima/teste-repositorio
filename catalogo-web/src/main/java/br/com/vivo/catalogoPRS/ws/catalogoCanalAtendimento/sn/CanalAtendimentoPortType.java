/**
 * CanalAtendimentoPortType.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.vivo.catalogoPRS.ws.catalogoCanalAtendimento.sn;

public interface CanalAtendimentoPortType extends java.rmi.Remote {
    public br.com.vivo.catalogoPRS.ws.catalogoCanalAtendimento.sn.ResultadoBuscarCanalAtendimentoCanalAtendimento[] buscarCanalAtendimento(br.com.vivo.catalogoPRS.ws.catalogoCanalAtendimento.sn.ParametroBuscarCanalAtendimento buscarCanalAtendimentoRequest, String username, String password) throws java.rmi.RemoteException, br.com.vivo.catalogoPRS.ws.catalogoCanalAtendimento.mc.geral.ErroInfo;
}
