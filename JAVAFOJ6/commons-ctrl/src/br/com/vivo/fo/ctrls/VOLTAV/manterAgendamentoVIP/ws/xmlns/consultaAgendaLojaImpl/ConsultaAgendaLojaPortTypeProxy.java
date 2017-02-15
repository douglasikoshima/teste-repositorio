package br.com.vivo.fo.ctrls.VOLTAV.manterAgendamentoVIP.ws.xmlns.consultaAgendaLojaImpl;

public class ConsultaAgendaLojaPortTypeProxy implements br.com.vivo.fo.ctrls.VOLTAV.manterAgendamentoVIP.ws.xmlns.consultaAgendaLojaImpl.ConsultaAgendaLojaPortType {
  private String _endpoint = null;
  private br.com.vivo.fo.ctrls.VOLTAV.manterAgendamentoVIP.ws.xmlns.consultaAgendaLojaImpl.ConsultaAgendaLojaPortType consultaAgendaLojaPortType = null;
  
  public ConsultaAgendaLojaPortTypeProxy() {
    _initConsultaAgendaLojaPortTypeProxy();
  }
  
  public ConsultaAgendaLojaPortTypeProxy(String endpoint) {
    _endpoint = endpoint;
    _initConsultaAgendaLojaPortTypeProxy();
  }
  
  private void _initConsultaAgendaLojaPortTypeProxy() {
    try {
      consultaAgendaLojaPortType = (new br.com.vivo.fo.ctrls.VOLTAV.manterAgendamentoVIP.ws.xmlns.consultaAgendaLojaImpl.SoapProcessLocator()).getSoapProcessHttpPort();
      if (consultaAgendaLojaPortType != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)consultaAgendaLojaPortType)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)consultaAgendaLojaPortType)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (consultaAgendaLojaPortType != null)
      ((javax.xml.rpc.Stub)consultaAgendaLojaPortType)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public br.com.vivo.fo.ctrls.VOLTAV.manterAgendamentoVIP.ws.xmlns.consultaAgendaLojaImpl.ConsultaAgendaLojaPortType getConsultaAgendaLojaPortType() {
    if (consultaAgendaLojaPortType == null)
      _initConsultaAgendaLojaPortTypeProxy();
    return consultaAgendaLojaPortType;
  }
  
  public br.com.vivo.fo.ctrls.VOLTAV.manterAgendamentoVIP.ws.xsd.consultaAgendaLoja.output.ConsultaAgendaLojaOutputType consultaAgendaLoja(br.com.vivo.fo.ctrls.VOLTAV.manterAgendamentoVIP.ws.xsd.consultaAgendaLoja.input.ConsultaAgendaLojaInputType input) throws java.rmi.RemoteException{
    if (consultaAgendaLojaPortType == null)
      _initConsultaAgendaLojaPortTypeProxy();
    return consultaAgendaLojaPortType.consultaAgendaLoja(input);
  }
  
  
}