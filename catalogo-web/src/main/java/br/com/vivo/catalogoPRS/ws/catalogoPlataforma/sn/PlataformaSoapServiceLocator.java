/**
 * PlataformaSoapServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.vivo.catalogoPRS.ws.catalogoPlataforma.sn;

public class PlataformaSoapServiceLocator extends org.apache.axis.client.Service implements br.com.vivo.catalogoPRS.ws.catalogoPlataforma.sn.PlataformaSoapService {

    public PlataformaSoapServiceLocator() {
    }


    public PlataformaSoapServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public PlataformaSoapServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for PlataformaSoapPort
    private java.lang.String PlataformaSoapPort_address = "http://alsb3-soa:80/CatalogoWeb/Plataforma/Proxy/PlataformaSecurity";

    public java.lang.String getPlataformaSoapPortAddress() {
        return PlataformaSoapPort_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String PlataformaSoapPortWSDDServiceName = "PlataformaSoapPort";

    public java.lang.String getPlataformaSoapPortWSDDServiceName() {
        return PlataformaSoapPortWSDDServiceName;
    }

    public void setPlataformaSoapPortWSDDServiceName(java.lang.String name) {
        PlataformaSoapPortWSDDServiceName = name;
    }

    public br.com.vivo.catalogoPRS.ws.catalogoPlataforma.sn.PlataformaPortType getPlataformaSoapPort() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(PlataformaSoapPort_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getPlataformaSoapPort(endpoint);
    }

    public br.com.vivo.catalogoPRS.ws.catalogoPlataforma.sn.PlataformaPortType getPlataformaSoapPort(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            br.com.vivo.catalogoPRS.ws.catalogoPlataforma.sn.PlataformaSoapBindingStub _stub = new br.com.vivo.catalogoPRS.ws.catalogoPlataforma.sn.PlataformaSoapBindingStub(portAddress, this);
            _stub.setPortName(getPlataformaSoapPortWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setPlataformaSoapPortEndpointAddress(java.lang.String address) {
        PlataformaSoapPort_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (br.com.vivo.catalogoPRS.ws.catalogoPlataforma.sn.PlataformaPortType.class.isAssignableFrom(serviceEndpointInterface)) {
                br.com.vivo.catalogoPRS.ws.catalogoPlataforma.sn.PlataformaSoapBindingStub _stub = new br.com.vivo.catalogoPRS.ws.catalogoPlataforma.sn.PlataformaSoapBindingStub(new java.net.URL(PlataformaSoapPort_address), this);
                _stub.setPortName(getPlataformaSoapPortWSDDServiceName());
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
        if ("PlataformaSoapPort".equals(inputPortName)) {
            return getPlataformaSoapPort();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoPlataforma", "PlataformaSoapService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoPlataforma", "PlataformaSoapPort"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("PlataformaSoapPort".equals(portName)) {
            setPlataformaSoapPortEndpointAddress(address);
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
