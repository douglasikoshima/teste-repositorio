/**
 * ModeloVendaServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.vivo.catalogoPRS.ws.catalogoModeloVenda.sn;

public class ModeloVendaServiceLocator extends org.apache.axis.client.Service implements br.com.vivo.catalogoPRS.ws.catalogoModeloVenda.sn.ModeloVendaService {

    public ModeloVendaServiceLocator() {
    }


    public ModeloVendaServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public ModeloVendaServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for ModeloVendaSoapPort
    private java.lang.String ModeloVendaSoapPort_address = "http://alsb3-soa:80/CatalogoWeb/ModeloVenda/Proxy/ModeloVendaSecurity";

    public java.lang.String getModeloVendaSoapPortAddress() {
        return ModeloVendaSoapPort_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String ModeloVendaSoapPortWSDDServiceName = "ModeloVendaSoapPort";

    public java.lang.String getModeloVendaSoapPortWSDDServiceName() {
        return ModeloVendaSoapPortWSDDServiceName;
    }

    public void setModeloVendaSoapPortWSDDServiceName(java.lang.String name) {
        ModeloVendaSoapPortWSDDServiceName = name;
    }

    public br.com.vivo.catalogoPRS.ws.catalogoModeloVenda.sn.ModeloVendaPortType getModeloVendaSoapPort() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(ModeloVendaSoapPort_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getModeloVendaSoapPort(endpoint);
    }

    public br.com.vivo.catalogoPRS.ws.catalogoModeloVenda.sn.ModeloVendaPortType getModeloVendaSoapPort(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            br.com.vivo.catalogoPRS.ws.catalogoModeloVenda.sn.ModeloVendaBindingStub _stub = new br.com.vivo.catalogoPRS.ws.catalogoModeloVenda.sn.ModeloVendaBindingStub(portAddress, this);
            _stub.setPortName(getModeloVendaSoapPortWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setModeloVendaSoapPortEndpointAddress(java.lang.String address) {
        ModeloVendaSoapPort_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (br.com.vivo.catalogoPRS.ws.catalogoModeloVenda.sn.ModeloVendaPortType.class.isAssignableFrom(serviceEndpointInterface)) {
                br.com.vivo.catalogoPRS.ws.catalogoModeloVenda.sn.ModeloVendaBindingStub _stub = new br.com.vivo.catalogoPRS.ws.catalogoModeloVenda.sn.ModeloVendaBindingStub(new java.net.URL(ModeloVendaSoapPort_address), this);
                _stub.setPortName(getModeloVendaSoapPortWSDDServiceName());
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
        if ("ModeloVendaSoapPort".equals(inputPortName)) {
            return getModeloVendaSoapPort();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoModeloVenda", "ModeloVendaService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoModeloVenda", "ModeloVendaSoapPort"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("ModeloVendaSoapPort".equals(portName)) {
            setModeloVendaSoapPortEndpointAddress(address);
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
