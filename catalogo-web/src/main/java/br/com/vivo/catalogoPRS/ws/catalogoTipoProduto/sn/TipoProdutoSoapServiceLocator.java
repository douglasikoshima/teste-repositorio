/**
 * TipoProdutoSoapServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.vivo.catalogoPRS.ws.catalogoTipoProduto.sn;

public class TipoProdutoSoapServiceLocator extends org.apache.axis.client.Service implements br.com.vivo.catalogoPRS.ws.catalogoTipoProduto.sn.TipoProdutoSoapService {

    public TipoProdutoSoapServiceLocator() {
    }


    public TipoProdutoSoapServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public TipoProdutoSoapServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for TipoProdutoSoapPort
    private java.lang.String TipoProdutoSoapPort_address = "http://alsb3-soa:80/CatalogoWeb/TipoProduto/Proxy/TipoProdutoSecurity";

    public java.lang.String getTipoProdutoSoapPortAddress() {
        return TipoProdutoSoapPort_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String TipoProdutoSoapPortWSDDServiceName = "TipoProdutoSoapPort";

    public java.lang.String getTipoProdutoSoapPortWSDDServiceName() {
        return TipoProdutoSoapPortWSDDServiceName;
    }

    public void setTipoProdutoSoapPortWSDDServiceName(java.lang.String name) {
        TipoProdutoSoapPortWSDDServiceName = name;
    }

    public br.com.vivo.catalogoPRS.ws.catalogoTipoProduto.sn.TipoProdutoPortType getTipoProdutoSoapPort() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(TipoProdutoSoapPort_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getTipoProdutoSoapPort(endpoint);
    }

    public br.com.vivo.catalogoPRS.ws.catalogoTipoProduto.sn.TipoProdutoPortType getTipoProdutoSoapPort(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            br.com.vivo.catalogoPRS.ws.catalogoTipoProduto.sn.TipoProdutoSoapBindingStub _stub = new br.com.vivo.catalogoPRS.ws.catalogoTipoProduto.sn.TipoProdutoSoapBindingStub(portAddress, this);
            _stub.setPortName(getTipoProdutoSoapPortWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setTipoProdutoSoapPortEndpointAddress(java.lang.String address) {
        TipoProdutoSoapPort_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (br.com.vivo.catalogoPRS.ws.catalogoTipoProduto.sn.TipoProdutoPortType.class.isAssignableFrom(serviceEndpointInterface)) {
                br.com.vivo.catalogoPRS.ws.catalogoTipoProduto.sn.TipoProdutoSoapBindingStub _stub = new br.com.vivo.catalogoPRS.ws.catalogoTipoProduto.sn.TipoProdutoSoapBindingStub(new java.net.URL(TipoProdutoSoapPort_address), this);
                _stub.setPortName(getTipoProdutoSoapPortWSDDServiceName());
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
        if ("TipoProdutoSoapPort".equals(inputPortName)) {
            return getTipoProdutoSoapPort();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoTipoProduto", "TipoProdutoSoapService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoTipoProduto", "TipoProdutoSoapPort"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("TipoProdutoSoapPort".equals(portName)) {
            setTipoProdutoSoapPortEndpointAddress(address);
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
