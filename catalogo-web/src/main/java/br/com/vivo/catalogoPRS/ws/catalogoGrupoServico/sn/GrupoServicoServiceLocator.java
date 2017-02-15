/**
 * GrupoServicoServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.vivo.catalogoPRS.ws.catalogoGrupoServico.sn;

public class GrupoServicoServiceLocator extends org.apache.axis.client.Service implements br.com.vivo.catalogoPRS.ws.catalogoGrupoServico.sn.GrupoServicoService {

    public GrupoServicoServiceLocator() {
    }


    public GrupoServicoServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public GrupoServicoServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for GrupoServicoSoapPort
    private java.lang.String GrupoServicoSoapPort_address = "http://alsb3-soa:80/CatalogoWeb/GrupoServico/Proxy/GrupoServicoSecurity";

    public java.lang.String getGrupoServicoSoapPortAddress() {
        return GrupoServicoSoapPort_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String GrupoServicoSoapPortWSDDServiceName = "GrupoServicoSoapPort";

    public java.lang.String getGrupoServicoSoapPortWSDDServiceName() {
        return GrupoServicoSoapPortWSDDServiceName;
    }

    public void setGrupoServicoSoapPortWSDDServiceName(java.lang.String name) {
        GrupoServicoSoapPortWSDDServiceName = name;
    }

    public br.com.vivo.catalogoPRS.ws.catalogoGrupoServico.sn.GrupoServicoPortType getGrupoServicoSoapPort() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(GrupoServicoSoapPort_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getGrupoServicoSoapPort(endpoint);
    }

    public br.com.vivo.catalogoPRS.ws.catalogoGrupoServico.sn.GrupoServicoPortType getGrupoServicoSoapPort(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            br.com.vivo.catalogoPRS.ws.catalogoGrupoServico.sn.GrupoServicoBindingStub _stub = new br.com.vivo.catalogoPRS.ws.catalogoGrupoServico.sn.GrupoServicoBindingStub(portAddress, this);
            _stub.setPortName(getGrupoServicoSoapPortWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setGrupoServicoSoapPortEndpointAddress(java.lang.String address) {
        GrupoServicoSoapPort_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (br.com.vivo.catalogoPRS.ws.catalogoGrupoServico.sn.GrupoServicoPortType.class.isAssignableFrom(serviceEndpointInterface)) {
                br.com.vivo.catalogoPRS.ws.catalogoGrupoServico.sn.GrupoServicoBindingStub _stub = new br.com.vivo.catalogoPRS.ws.catalogoGrupoServico.sn.GrupoServicoBindingStub(new java.net.URL(GrupoServicoSoapPort_address), this);
                _stub.setPortName(getGrupoServicoSoapPortWSDDServiceName());
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
        if ("GrupoServicoSoapPort".equals(inputPortName)) {
            return getGrupoServicoSoapPort();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoGrupoServico", "GrupoServicoService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoGrupoServico", "GrupoServicoSoapPort"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("GrupoServicoSoapPort".equals(portName)) {
            setGrupoServicoSoapPortEndpointAddress(address);
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
