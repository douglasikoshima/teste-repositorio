/**
 * AcaoSoapService.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.vivo.catalogoPRS.ws.catalogoAcao.sn;

public interface AcaoSoapService extends javax.xml.rpc.Service {
    public java.lang.String getAcaoSOAPAddress();

    public br.com.vivo.catalogoPRS.ws.catalogoAcao.sn.AcaoPortType getAcaoSOAP() throws javax.xml.rpc.ServiceException;

    public br.com.vivo.catalogoPRS.ws.catalogoAcao.sn.AcaoPortType getAcaoSOAP(java.net.URL portAddress) throws javax.xml.rpc.ServiceException;
}
