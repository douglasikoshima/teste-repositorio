/**
 * SoapProcessLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.vivo.fo.ctrls.VOLTAV.manterAgendamentoVIP.ws.xmlns.consultaAgendaLojaImpl;

public class SoapProcessLocator extends org.apache.axis.client.Service implements br.com.vivo.fo.ctrls.VOLTAV.manterAgendamentoVIP.ws.xmlns.consultaAgendaLojaImpl.SoapProcess {

    public SoapProcessLocator() {
    }


    public SoapProcessLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public SoapProcessLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for SoapProcessHttpPort
    private java.lang.String SoapProcessHttpPort_address = "http://10.128.200.41:9009/Main_sp_FRONTOFFICE/Services_sp_EAI/ConsultaAgendaLoja/Conector_sp_Process/SoapProcess";

    public java.lang.String getSoapProcessHttpPortAddress() {
        return SoapProcessHttpPort_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String SoapProcessHttpPortWSDDServiceName = "SoapProcessHttpPort";

    public java.lang.String getSoapProcessHttpPortWSDDServiceName() {
        return SoapProcessHttpPortWSDDServiceName;
    }

    public void setSoapProcessHttpPortWSDDServiceName(java.lang.String name) {
        SoapProcessHttpPortWSDDServiceName = name;
    }

    public br.com.vivo.fo.ctrls.VOLTAV.manterAgendamentoVIP.ws.xmlns.consultaAgendaLojaImpl.ConsultaAgendaLojaPortType getSoapProcessHttpPort() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(SoapProcessHttpPort_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getSoapProcessHttpPort(endpoint);
    }

    public br.com.vivo.fo.ctrls.VOLTAV.manterAgendamentoVIP.ws.xmlns.consultaAgendaLojaImpl.ConsultaAgendaLojaPortType getSoapProcessHttpPort(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            br.com.vivo.fo.ctrls.VOLTAV.manterAgendamentoVIP.ws.xmlns.consultaAgendaLojaImpl.SoapProcessBindingStub _stub = new br.com.vivo.fo.ctrls.VOLTAV.manterAgendamentoVIP.ws.xmlns.consultaAgendaLojaImpl.SoapProcessBindingStub(portAddress, this);
            _stub.setPortName(getSoapProcessHttpPortWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setSoapProcessHttpPortEndpointAddress(java.lang.String address) {
        SoapProcessHttpPort_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (br.com.vivo.fo.ctrls.VOLTAV.manterAgendamentoVIP.ws.xmlns.consultaAgendaLojaImpl.ConsultaAgendaLojaPortType.class.isAssignableFrom(serviceEndpointInterface)) {
                br.com.vivo.fo.ctrls.VOLTAV.manterAgendamentoVIP.ws.xmlns.consultaAgendaLojaImpl.SoapProcessBindingStub _stub = new br.com.vivo.fo.ctrls.VOLTAV.manterAgendamentoVIP.ws.xmlns.consultaAgendaLojaImpl.SoapProcessBindingStub(new java.net.URL(SoapProcessHttpPort_address), this);
                _stub.setPortName(getSoapProcessHttpPortWSDDServiceName());
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
        if ("SoapProcessHttpPort".equals(inputPortName)) {
            return getSoapProcessHttpPort();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://xmlns.example.com/1205183289593/consultaAgendaLojaImpl", "SoapProcess");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://xmlns.example.com/1205183289593/consultaAgendaLojaImpl", "SoapProcessHttpPort"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("SoapProcessHttpPort".equals(portName)) {
            setSoapProcessHttpPortEndpointAddress(address);
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
