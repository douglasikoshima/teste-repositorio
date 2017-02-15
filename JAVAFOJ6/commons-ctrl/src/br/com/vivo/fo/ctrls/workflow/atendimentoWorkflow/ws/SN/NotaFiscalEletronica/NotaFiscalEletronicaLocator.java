/**
 * NotaFiscalEletronicaLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.vivo.fo.ctrls.workflow.atendimentoWorkflow.ws.SN.NotaFiscalEletronica;

public class NotaFiscalEletronicaLocator extends org.apache.axis.client.Service implements br.com.vivo.fo.ctrls.workflow.atendimentoWorkflow.ws.SN.NotaFiscalEletronica.NotaFiscalEletronica {

    public NotaFiscalEletronicaLocator() {
    }


    public NotaFiscalEletronicaLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public NotaFiscalEletronicaLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for NotaFiscalEletronicaPort
    private java.lang.String NotaFiscalEletronicaPort_address = "http://alsb3-soa/NotaFiscalEletronica";

    public java.lang.String getNotaFiscalEletronicaPortAddress() {
        return NotaFiscalEletronicaPort_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String NotaFiscalEletronicaPortWSDDServiceName = "NotaFiscalEletronicaPort";

    public java.lang.String getNotaFiscalEletronicaPortWSDDServiceName() {
        return NotaFiscalEletronicaPortWSDDServiceName;
    }

    public void setNotaFiscalEletronicaPortWSDDServiceName(java.lang.String name) {
        NotaFiscalEletronicaPortWSDDServiceName = name;
    }

    public br.com.vivo.fo.ctrls.workflow.atendimentoWorkflow.ws.SN.NotaFiscalEletronica.NotaFiscalEletronicaPortType getNotaFiscalEletronicaPort() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(NotaFiscalEletronicaPort_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getNotaFiscalEletronicaPort(endpoint);
    }

    public br.com.vivo.fo.ctrls.workflow.atendimentoWorkflow.ws.SN.NotaFiscalEletronica.NotaFiscalEletronicaPortType getNotaFiscalEletronicaPort(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            br.com.vivo.fo.ctrls.workflow.atendimentoWorkflow.ws.SN.NotaFiscalEletronica.NotaFiscalEletronicaBindingStub _stub = new br.com.vivo.fo.ctrls.workflow.atendimentoWorkflow.ws.SN.NotaFiscalEletronica.NotaFiscalEletronicaBindingStub(portAddress, this);
            _stub.setPortName(getNotaFiscalEletronicaPortWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setNotaFiscalEletronicaPortEndpointAddress(java.lang.String address) {
        NotaFiscalEletronicaPort_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (br.com.vivo.fo.ctrls.workflow.atendimentoWorkflow.ws.SN.NotaFiscalEletronica.NotaFiscalEletronicaPortType.class.isAssignableFrom(serviceEndpointInterface)) {
                br.com.vivo.fo.ctrls.workflow.atendimentoWorkflow.ws.SN.NotaFiscalEletronica.NotaFiscalEletronicaBindingStub _stub = new br.com.vivo.fo.ctrls.workflow.atendimentoWorkflow.ws.SN.NotaFiscalEletronica.NotaFiscalEletronicaBindingStub(new java.net.URL(NotaFiscalEletronicaPort_address), this);
                _stub.setPortName(getNotaFiscalEletronicaPortWSDDServiceName());
                return _stub;
            }
        }
        catch (java.lang.Throwable t) {
            throw new javax.xml.rpc.ServiceException(t);
        }
        throw new javax.xml.rpc.ServiceException("There is no stub implementation for the interface:  " + (serviceEndpointInterface == null ? "null" : serviceEndpointInterface.getName()));
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(javax.xml.namespace.QName portName, Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        if (portName == null) {
            return getPort(serviceEndpointInterface);
        }
        java.lang.String inputPortName = portName.getLocalPart();
        if ("NotaFiscalEletronicaPort".equals(inputPortName)) {
            return getNotaFiscalEletronicaPort();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://www.vivo.com.br/SN/NotaFiscalEletronica", "NotaFiscalEletronica");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/NotaFiscalEletronica", "NotaFiscalEletronicaPort"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("NotaFiscalEletronicaPort".equals(portName)) {
            setNotaFiscalEletronicaPortEndpointAddress(address);
        }
        else 
{ // Unknown Port Name
            throw new javax.xml.rpc.ServiceException(" Cannot set Endpoint Address for Unknown Port" + portName);
        }
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(javax.xml.namespace.QName portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        setEndpointAddress(portName.getLocalPart(), address);
    }

}
