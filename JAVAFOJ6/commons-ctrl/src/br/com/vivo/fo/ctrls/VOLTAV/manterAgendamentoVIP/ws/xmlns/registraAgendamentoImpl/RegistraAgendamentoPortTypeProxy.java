package br.com.vivo.fo.ctrls.VOLTAV.manterAgendamentoVIP.ws.xmlns.registraAgendamentoImpl;

public class RegistraAgendamentoPortTypeProxy implements br.com.vivo.fo.ctrls.VOLTAV.manterAgendamentoVIP.ws.xmlns.registraAgendamentoImpl.RegistraAgendamentoPortType {
  private String _endpoint = null;
  private br.com.vivo.fo.ctrls.VOLTAV.manterAgendamentoVIP.ws.xmlns.registraAgendamentoImpl.RegistraAgendamentoPortType registraAgendamentoPortType = null;
  
  public RegistraAgendamentoPortTypeProxy() {
    _initRegistraAgendamentoPortTypeProxy();
  }
  
  public RegistraAgendamentoPortTypeProxy(String endpoint) {
    _endpoint = endpoint;
    _initRegistraAgendamentoPortTypeProxy();
  }
  
  private void _initRegistraAgendamentoPortTypeProxy() {
    try {
      registraAgendamentoPortType = (new br.com.vivo.fo.ctrls.VOLTAV.manterAgendamentoVIP.ws.xmlns.registraAgendamentoImpl.SoapProcessLocator()).getSoapProcessHttpPort();
      if (registraAgendamentoPortType != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)registraAgendamentoPortType)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)registraAgendamentoPortType)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (registraAgendamentoPortType != null)
      ((javax.xml.rpc.Stub)registraAgendamentoPortType)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public br.com.vivo.fo.ctrls.VOLTAV.manterAgendamentoVIP.ws.xmlns.registraAgendamentoImpl.RegistraAgendamentoPortType getRegistraAgendamentoPortType() {
    if (registraAgendamentoPortType == null)
      _initRegistraAgendamentoPortTypeProxy();
    return registraAgendamentoPortType;
  }
  
  public br.com.vivo.fo.ctrls.VOLTAV.manterAgendamentoVIP.ws.xsd.registraAgendamento.output.RegistraAgendamentoOutputType registraAgendamento(br.com.vivo.fo.ctrls.VOLTAV.manterAgendamentoVIP.ws.xsd.registraAgendamento.input.RegistraAgendamentoInputType input) throws java.rmi.RemoteException{
    if (registraAgendamentoPortType == null)
      _initRegistraAgendamentoPortTypeProxy();
    return registraAgendamentoPortType.registraAgendamento(input);
  }
  
  
}