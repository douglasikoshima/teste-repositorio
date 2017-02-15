/**
 * ServicoSoapServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.vivo.catalogoPRS.ws.catalogoServico.sn;

public class ServicoSoapServiceLocator extends org.apache.axis.client.Service implements br.com.vivo.catalogoPRS.ws.catalogoServico.sn.ServicoSoapService {

    public ServicoSoapServiceLocator() {
    }


    public ServicoSoapServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public ServicoSoapServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for ServicoSoapPort
    private java.lang.String ServicoSoapPort_address = "http://alsb3-soa:80/CatalogoWeb/Servico/Proxy/ServicoSecurity";

    public java.lang.String getServicoSoapPortAddress() {
        return ServicoSoapPort_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String ServicoSoapPortWSDDServiceName = "ServicoSoapPort";

    public java.lang.String getServicoSoapPortWSDDServiceName() {
        return ServicoSoapPortWSDDServiceName;
    }

    public void setServicoSoapPortWSDDServiceName(java.lang.String name) {
        ServicoSoapPortWSDDServiceName = name;
    }

    public br.com.vivo.catalogoPRS.ws.catalogoServico.sn.ServicoPortType getServicoSoapPort() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(ServicoSoapPort_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getServicoSoapPort(endpoint);
    }

    public br.com.vivo.catalogoPRS.ws.catalogoServico.sn.ServicoPortType getServicoSoapPort(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            br.com.vivo.catalogoPRS.ws.catalogoServico.sn.ServicoBindingStub _stub = new br.com.vivo.catalogoPRS.ws.catalogoServico.sn.ServicoBindingStub(portAddress, this);
            _stub.setPortName(getServicoSoapPortWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setServicoSoapPortEndpointAddress(java.lang.String address) {
        ServicoSoapPort_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (br.com.vivo.catalogoPRS.ws.catalogoServico.sn.ServicoPortType.class.isAssignableFrom(serviceEndpointInterface)) {
                br.com.vivo.catalogoPRS.ws.catalogoServico.sn.ServicoBindingStub _stub = new br.com.vivo.catalogoPRS.ws.catalogoServico.sn.ServicoBindingStub(new java.net.URL(ServicoSoapPort_address), this);
                _stub.setPortName(getServicoSoapPortWSDDServiceName());
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
        if ("ServicoSoapPort".equals(inputPortName)) {
            return getServicoSoapPort();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoServico", "ServicoSoapService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoServico", "ServicoSoapPort"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("ServicoSoapPort".equals(portName)) {
            setServicoSoapPortEndpointAddress(address);
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
