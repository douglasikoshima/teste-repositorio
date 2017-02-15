/**
 * ProdutosSoapServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.vivo.catalogoPRS.ws.catalogoProduto.sn;

public class ProdutosSoapServiceLocator extends org.apache.axis.client.Service implements br.com.vivo.catalogoPRS.ws.catalogoProduto.sn.ProdutosSoapService {

    public ProdutosSoapServiceLocator() {
    }


    public ProdutosSoapServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public ProdutosSoapServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for ProdutosSoapPort
    private java.lang.String ProdutosSoapPort_address = "http://alsb3-soa:80/CatalogoWeb/Produto/Proxy/ProdutoSecurity";

    public java.lang.String getProdutosSoapPortAddress() {
        return ProdutosSoapPort_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String ProdutosSoapPortWSDDServiceName = "ProdutosSoapPort";

    public java.lang.String getProdutosSoapPortWSDDServiceName() {
        return ProdutosSoapPortWSDDServiceName;
    }

    public void setProdutosSoapPortWSDDServiceName(java.lang.String name) {
        ProdutosSoapPortWSDDServiceName = name;
    }

    public br.com.vivo.catalogoPRS.ws.catalogoProduto.sn.ProdutosPortType getProdutosSoapPort() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(ProdutosSoapPort_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getProdutosSoapPort(endpoint);
    }

    public br.com.vivo.catalogoPRS.ws.catalogoProduto.sn.ProdutosPortType getProdutosSoapPort(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            br.com.vivo.catalogoPRS.ws.catalogoProduto.sn.ProdutosBindingStub _stub = new br.com.vivo.catalogoPRS.ws.catalogoProduto.sn.ProdutosBindingStub(portAddress, this);
            _stub.setPortName(getProdutosSoapPortWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setProdutosSoapPortEndpointAddress(java.lang.String address) {
        ProdutosSoapPort_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (br.com.vivo.catalogoPRS.ws.catalogoProduto.sn.ProdutosPortType.class.isAssignableFrom(serviceEndpointInterface)) {
                br.com.vivo.catalogoPRS.ws.catalogoProduto.sn.ProdutosBindingStub _stub = new br.com.vivo.catalogoPRS.ws.catalogoProduto.sn.ProdutosBindingStub(new java.net.URL(ProdutosSoapPort_address), this);
                _stub.setPortName(getProdutosSoapPortWSDDServiceName());
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
        if ("ProdutosSoapPort".equals(inputPortName)) {
            return getProdutosSoapPort();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoProduto", "ProdutosSoapService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoProduto", "ProdutosSoapPort"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("ProdutosSoapPort".equals(portName)) {
            setProdutosSoapPortEndpointAddress(address);
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
