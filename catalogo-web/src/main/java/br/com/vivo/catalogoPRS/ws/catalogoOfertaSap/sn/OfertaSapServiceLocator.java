/**
 * OfertaSapServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.vivo.catalogoPRS.ws.catalogoOfertaSap.sn;

public class OfertaSapServiceLocator extends org.apache.axis.client.Service implements br.com.vivo.catalogoPRS.ws.catalogoOfertaSap.sn.OfertaSapService {

    public OfertaSapServiceLocator() {
    }


    public OfertaSapServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public OfertaSapServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for OfertaSapSoapPort
    private java.lang.String OfertaSapSoapPort_address = "http://alsb3-soa:80/CatalogoWeb/OfertaSap/Proxy/OfertaSapSecurity";

    public java.lang.String getOfertaSapSoapPortAddress() {
        return OfertaSapSoapPort_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String OfertaSapSoapPortWSDDServiceName = "OfertaSapSoapPort";

    public java.lang.String getOfertaSapSoapPortWSDDServiceName() {
        return OfertaSapSoapPortWSDDServiceName;
    }

    public void setOfertaSapSoapPortWSDDServiceName(java.lang.String name) {
        OfertaSapSoapPortWSDDServiceName = name;
    }

    public br.com.vivo.catalogoPRS.ws.catalogoOfertaSap.sn.OfertaSapPortType getOfertaSapSoapPort() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(OfertaSapSoapPort_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getOfertaSapSoapPort(endpoint);
    }

    public br.com.vivo.catalogoPRS.ws.catalogoOfertaSap.sn.OfertaSapPortType getOfertaSapSoapPort(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            br.com.vivo.catalogoPRS.ws.catalogoOfertaSap.sn.OfertaSapBindingStub _stub = new br.com.vivo.catalogoPRS.ws.catalogoOfertaSap.sn.OfertaSapBindingStub(portAddress, this);
            _stub.setPortName(getOfertaSapSoapPortWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setOfertaSapSoapPortEndpointAddress(java.lang.String address) {
        OfertaSapSoapPort_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (br.com.vivo.catalogoPRS.ws.catalogoOfertaSap.sn.OfertaSapPortType.class.isAssignableFrom(serviceEndpointInterface)) {
                br.com.vivo.catalogoPRS.ws.catalogoOfertaSap.sn.OfertaSapBindingStub _stub = new br.com.vivo.catalogoPRS.ws.catalogoOfertaSap.sn.OfertaSapBindingStub(new java.net.URL(OfertaSapSoapPort_address), this);
                _stub.setPortName(getOfertaSapSoapPortWSDDServiceName());
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
        if ("OfertaSapSoapPort".equals(inputPortName)) {
            return getOfertaSapSoapPort();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoOfertaSap", "OfertaSapService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoOfertaSap", "OfertaSapSoapPort"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("OfertaSapSoapPort".equals(portName)) {
            setOfertaSapSoapPortEndpointAddress(address);
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
