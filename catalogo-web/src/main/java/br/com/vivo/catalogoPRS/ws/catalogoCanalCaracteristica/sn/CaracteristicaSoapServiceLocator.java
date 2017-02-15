/**
 * CaracteristicaSoapServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.vivo.catalogoPRS.ws.catalogoCanalCaracteristica.sn;

public class CaracteristicaSoapServiceLocator extends org.apache.axis.client.Service implements br.com.vivo.catalogoPRS.ws.catalogoCanalCaracteristica.sn.CaracteristicaSoapService {

    public CaracteristicaSoapServiceLocator() {
    }


    public CaracteristicaSoapServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public CaracteristicaSoapServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for CaracteristicaSoapPort
    private java.lang.String CaracteristicaSoapPort_address = "http://alsb3-soa:80/CatalogoWeb/Caracteristica/Proxy/CaracteristicaSecurity";

    public java.lang.String getCaracteristicaSoapPortAddress() {
        return CaracteristicaSoapPort_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String CaracteristicaSoapPortWSDDServiceName = "CaracteristicaSoapPort";

    public java.lang.String getCaracteristicaSoapPortWSDDServiceName() {
        return CaracteristicaSoapPortWSDDServiceName;
    }

    public void setCaracteristicaSoapPortWSDDServiceName(java.lang.String name) {
        CaracteristicaSoapPortWSDDServiceName = name;
    }

    public br.com.vivo.catalogoPRS.ws.catalogoCanalCaracteristica.sn.CaracteristicaPortType getCaracteristicaSoapPort() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(CaracteristicaSoapPort_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getCaracteristicaSoapPort(endpoint);
    }

    public br.com.vivo.catalogoPRS.ws.catalogoCanalCaracteristica.sn.CaracteristicaPortType getCaracteristicaSoapPort(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            br.com.vivo.catalogoPRS.ws.catalogoCanalCaracteristica.sn.CaracteristicaSoapBindingStub _stub = new br.com.vivo.catalogoPRS.ws.catalogoCanalCaracteristica.sn.CaracteristicaSoapBindingStub(portAddress, this);
            _stub.setPortName(getCaracteristicaSoapPortWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setCaracteristicaSoapPortEndpointAddress(java.lang.String address) {
        CaracteristicaSoapPort_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (br.com.vivo.catalogoPRS.ws.catalogoCanalCaracteristica.sn.CaracteristicaPortType.class.isAssignableFrom(serviceEndpointInterface)) {
                br.com.vivo.catalogoPRS.ws.catalogoCanalCaracteristica.sn.CaracteristicaSoapBindingStub _stub = new br.com.vivo.catalogoPRS.ws.catalogoCanalCaracteristica.sn.CaracteristicaSoapBindingStub(new java.net.URL(CaracteristicaSoapPort_address), this);
                _stub.setPortName(getCaracteristicaSoapPortWSDDServiceName());
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
        if ("CaracteristicaSoapPort".equals(inputPortName)) {
            return getCaracteristicaSoapPort();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoCaracteristica", "CaracteristicaSoapService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoCaracteristica", "CaracteristicaSoapPort"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("CaracteristicaSoapPort".equals(portName)) {
            setCaracteristicaSoapPortEndpointAddress(address);
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
